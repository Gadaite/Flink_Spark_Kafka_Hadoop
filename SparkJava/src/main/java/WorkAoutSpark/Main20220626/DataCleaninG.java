package WorkAoutSpark.Main20220626;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import GadaiteToolGeo.LonLatToGeoHash;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * made by Gadaite
 * 由于一天的数据点数太多了，因此现在对数据的轨迹点转GeoHash进行数据的清晰
 * 使用自定义的GeoHash规则进行Hash加密
 * 先按照天数进行分组，对每天的数据的每个GeoHash值只选取一个点
 */
public class DataCleaninG {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("DataCleaning", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from trajectlonlat");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        JavaRDD<Row> dataSourceRDD = dataset.toJavaRDD();
        //  原始数据的条目数
        System.out.println("原始数据的条目数 : " + dataset.count());//  原始数据的条目数 : 24876881
        //  将数据转换为天数与数据的元组键值对RDD
        //  按照键进行分组
        //  对每个组内的数据转换为GeoHash,精度为9
        //  对组内相同的GeoHash继续去重，即只选择一个点即可
        JavaRDD<Row> ResRDD = dataSourceRDD.mapToPair(new PairFunction<Row, String, Row>() {
            @Override
            public Tuple2<String, Row> call(Row row) throws Exception {
                Timestamp datetime = row.getTimestamp(row.fieldIndex("datetime"));
                String currentDate = format.format(datetime);
                return new Tuple2<>(currentDate, row);
            }
        }).groupByKey().map(new Function<Tuple2<String, Iterable<Row>>, Iterable<Row>>() {
            @Override
            public Iterable<Row> call(Tuple2<String, Iterable<Row>> v1) throws Exception {
                TreeMap<String, Row> treeMap = new TreeMap<>();
                List<Row> rows = new ArrayList<>();
                v1._2.forEach(x -> {
                    double latitude = x.getDouble(x.fieldIndex("latitude"));
                    double longitude = x.getDouble(x.fieldIndex("longitude"));
                    double altitude = x.getDouble(x.fieldIndex("altitude"));
                    Timestamp datetime = x.getTimestamp(x.fieldIndex("datetime"));
//                    String geoHash = new LonLatToGeoHash().getGeoHash(longitude, latitude, 9);
                    String geoHash = new LonLatToGeoHash().getGeoHash(longitude, latitude, 8);
                    treeMap.put(geoHash, x);
                });
                Set<Map.Entry<String, Row>> entries = treeMap.entrySet();
                for (Map.Entry<String, Row> entry : entries) {
                    rows.add(entry.getValue());
                }
                return rows;
            }
        }).flatMap(new FlatMapFunction<Iterable<Row>, Row>() {
            @Override
            public Iterator<Row> call(Iterable<Row> rows) throws Exception {
                return rows.iterator();
            }
        });
        String gadaiteDDL = new GetDDL().GetGadaiteDDL(dataset);//  latitude:double,longitude:double,altitude:double,datetime:timestamp
        String defaultDDL = new GetDDL().GetDefaultDDL(dataset);
        Dataset<Row> dataFrame = spark.createDataFrame(ResRDD, StructType.fromDDL(defaultDDL));
        //  处理后的数据的条目数
        System.out.println("清洗之后的条目数 : " + dataFrame.count());
        //  清洗数据入库
//        pcon.PushToPSql(dataFrame,"trajectlonlathash9","overwrite");  //  清洗之后的条目数 : 16707092
        pcon.PushToPSql(dataFrame,"trajectlonlathash8","overwrite");//  清洗之后的条目数 : 8554180
    }
}
