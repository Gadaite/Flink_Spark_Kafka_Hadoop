package WorkAoutSpark.Main20220613;


import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import java.util.*;

/**
 * json格式文件数据提取处理
 * 转换JTS对应类型
 * 构造空间几何区域结构
 * 入库到postgresSql，使用Postgis查看
 * (Mysql难以存放Geometry的数据类型,且轨迹数据一般较长，Mysql会将字段转换为text，总之就是不方便)
 */
public class ChinaCityBoundary {
    public static void main(String[] args) throws Exception {
        /**
         * 创建Spark执行环境
         */
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("CityBoundary", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        /**
         * 读取Json文件，并解析到List<list>中
         */
        JavaRDD<String> javaRDD = jsc.textFile("file:\\D:\\googledownload\\china.json");
        List<String> collect = javaRDD.collect();
        String s = collect.get(0);
        JsonParser jsonParser = new JsonParser();
        JsonObject jread = jsonParser.parse(s).getAsJsonObject();
        JsonArray array = jread.get("features").getAsJsonArray();
        List<List<String>> list = new ArrayList<>();
        int len = array.size();
        for (int i =0 ;i<len -1;i++){
            /**
             * 先不处理最后一条数据
             */
            ArrayList<String> lineData = new ArrayList<>();
            JsonObject dataRow = array.get(i).getAsJsonObject().get("properties").getAsJsonObject();
            String adcode = dataRow.get("adcode").getAsString();
            lineData.add(adcode);
            String name = dataRow.get("name").getAsString();
            lineData.add(name);
            String center = dataRow.get("center").getAsJsonArray().toString();
            lineData.add(center);
            String centroid = dataRow.get("centroid").getAsJsonArray().toString();
            lineData.add(centroid);
            String childrenNum = dataRow.get("childrenNum").getAsString();
            lineData.add(childrenNum);
            String level = dataRow.get("level").getAsString();
            lineData.add(level);
            String parentCenter = dataRow.get("parent").getAsJsonObject().get("adcode").getAsString();
            lineData.add(parentCenter);
            String subFeatureIndex = dataRow.get("subFeatureIndex").getAsString();
            lineData.add(subFeatureIndex);
            String acroutes = dataRow.get("acroutes").getAsString();
            lineData.add(acroutes);
            String geometry = array.get(i).getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
                    .get(0).getAsJsonArray().get(0).getAsJsonArray().toString();
            lineData.add(geometry);
            list.add(lineData);
        }
        /**
         * 创建RDD
         */
        ArrayList<Row> rows = new ArrayList<>();
        list.forEach(x ->{
            rows.add(RowFactory.create(x));
        });
        JavaRDD<Row> rdd = jsc.parallelize(rows);
        /**
         * 转换实体类RDD
         * 查看结果便于数据的修改
         */
        JavaRDD<CityBoundaryModel> transrdd = rdd.map(new TransToEntity());
//        transrdd.foreach(x-> System.out.println(x));
        /**
         * 转换为对应的字符串类型，便于上传
         * 创建DataSet
         * 将geometry列名进行重命名为:
         */
        JavaRDD<ChinaCityBean> resRDD = transrdd.map(new TransToBean());
//        resRDD.foreach(x-> System.out.println(x));
        Dataset<Row> dataFrame = spark.createDataFrame(resRDD, ChinaCityBean.class);
        Dataset<Row> dataset = dataFrame.withColumnRenamed("geometry", "region");
        dataset.show(34);
        /**
         * 执行数据写入并更改表结构
         */
//        pcon.PushToPSql(dataset,"ChinaCityBoundary","overwrite");
        PostgresqlConnect postgresqlConnect = new PostgresqlConnect();
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN region TYPE geometry USING region::geometry;");
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN center TYPE geometry USING center::geometry;");
        postgresqlConnect.ExecPSql("ALTER TABLE public.ChinaCityBoundary ALTER COLUMN centroid TYPE geometry USING centroid::geometry;");
        /**
         * 把之前没有处理的最后一条数据单独放进一个新的表:test
         * 看一看究竟是什么样的
         */
        System.out.println(array.get(len - 1).getAsJsonObject().get("properties"));
        //  output:{"name":"","adchar":"JD","adcode":"100000_JD"}
        String ss = array.get(len - 1).getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
                .get(0).getAsJsonArray().get(0).getAsJsonArray().toString();
        System.out.println();
        //  ooutput:[[122.51865306,23.46078502],[122.79861399,24.57367379],[122.79889322,24.57678999],...........,...........(较多数据)
        /**
         * 下面内容从TransToEntity，拿出来就单独测试试了，
         * 没必要重新使用Spark的RDD的Map算子
         */
        String replace = ss.replace("[", "").replace("]", "").replace(" ", "");
        System.out.println(replace);
        String[] split = replace.split(",");
        GeometryFactory geometryFactory = new GeometryFactory();
        List<Double> coord = new ArrayList<>();
        for (int i= 11;i<split.length;i++){
            coord.add(Double.valueOf(split[i]));
        }
        List<Coordinate> coordinateslist = new ArrayList<>();
        for (int i=0;i<coord.size()/2;i++){
            coordinateslist.add(new Coordinate(coord.get(2*i),coord.get(2*i +1)));
        }
        coordinateslist.add(coordinateslist.get(coordinateslist.size() - 1));
        /**
         * Exception in thread "main" java.lang.IllegalArgumentException: Points of LinearRing do not form a closed linestring
         * 此在上边将其强行闭合,但是还是不能解决，调试发现(24.58458272, 122.79135575, NaN)为coordinateslist的元素结构
         * 但是正常的数据调试时也是一样的，问题不是出现在这里
         * 怀疑是所有点都共线的问题导致的，取消最后加点，转为查看
         */
        coordinateslist.remove(coordinateslist.get(coordinateslist.size() - 1));
        LineString lineString = geometryFactory.createLineString(coordinateslist.toArray(new Coordinate[coordinateslist.size()]));
        System.out.println(lineString.toString());
        /**
         * 创建Datase，提交新表Test，修改数据类型，并进行查看
         */
        JavaRDD<Row> lastdata = jsc.parallelize(Arrays.asList(
                RowFactory.create(lineString.toString())
        ));
        Dataset<Row> region_test = spark.createDataFrame(lastdata, StructType.fromDDL("region String"));
//        pcon.PushToPSql(region_test,"test","overwrite");
//        postgresqlConnect.ExecPSql("ALTER TABLE public.test ALTER COLUMN region TYPE geometry USING region::geometry;");
        /**
         * pg库上使用postgis插件证实就是一条直线
         * 将上面入库改表结构的部分注释掉
         */

    }
}
