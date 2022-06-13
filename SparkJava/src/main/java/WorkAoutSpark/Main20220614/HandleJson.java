package WorkAoutSpark.Main20220614;

import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import WorkAoutSpark.Main20220613.ChinaCityBean;
import WorkAoutSpark.Main20220613.CityBoundaryModel;
import WorkAoutSpark.Main20220613.TransToBean;
import WorkAoutSpark.Main20220613.TransToEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HandleJson {
    public static void main(String[] args) throws Exception {
        JsonParser jsonParser = new JsonParser();
        File file = new File("C:\\Users\\Gadaite\\Desktop\\sichuan.json");
        FileReader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        System.out.println(parse.toString());
        JsonObject object = parse.getAsJsonObject();
        JsonArray features = object.get("features").getAsJsonArray();
        int size = features.size();
        List<List<String>> list = new ArrayList<>();
        for (int i =0;i<size;i++){
            ArrayList<String> lineData = new ArrayList<>();
            JsonObject properties = features.get(i).getAsJsonObject().get("properties").getAsJsonObject();
            String adcode = properties.get("adcode").getAsString();
            lineData.add(adcode);
            String name = properties.get("name").getAsString();
            lineData.add(name);
            String center = properties.get("center").getAsJsonArray().toString();
            lineData.add(center);
            String centroid = properties.get("centroid").getAsJsonArray().toString();
            lineData.add(centroid);
            String childrenNum = properties.get("childrenNum").getAsString();
            lineData.add(childrenNum);
            String level = properties.get("level").getAsString();
            lineData.add(level);
            String parentCenter = properties.get("parent").getAsJsonObject().get("adcode").getAsString();
            lineData.add(parentCenter);
            String subFeatureIndex = properties.get("subFeatureIndex").getAsString();
            lineData.add(subFeatureIndex);
            JsonElement acroutes1 = properties.get("acroutes");
            String acroutes = properties.get("acroutes").toString();
            lineData.add(acroutes);
            String geometry = features.get(i).getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
                    .get(0).getAsJsonArray().get(0).getAsJsonArray().toString();
            lineData.add(geometry);
            list.add(lineData);
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("City2Boundary", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        ArrayList<Row> rows = new ArrayList<>();
        /**
         * 在执行时报错java.lang.IllegalArgumentException: Points of LinearRing do not form a closed linestring
         * 因此加入起始点到末尾去构造封闭区域,但是还是报告相同的错误
         */
//        list.add(list.get(list.size()-1));
        list.forEach(x ->{
            rows.add(RowFactory.create(x));
        });
        JavaRDD<Row> rdd = jsc.parallelize(rows);
        JavaRDD<CityBoundaryModel> transrdd = rdd.map(new TransToEntity());
        JavaRDD<ChinaCityBean> resRDD = transrdd.map(new TransToBean());
//        resRDD.foreach(x-> System.out.println(x));
        Dataset<Row> dataFrame = spark.createDataFrame(resRDD, ChinaCityBean.class);
        Dataset<Row> dataset = dataFrame.withColumnRenamed("geometry", "region");
        dataset.show(34);
        /**
         * 执行数据写入并更改表结构
         */
        pcon.PushToPSql(dataset,"ChinaCity2Boundary","overwrite");
        PostgresqlConnect postgresqlConnect = new PostgresqlConnect();
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN region TYPE geometry USING region::geometry;");
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN center TYPE geometry USING center::geometry;");
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN centroid TYPE geometry USING centroid::geometry;");

    }
}
