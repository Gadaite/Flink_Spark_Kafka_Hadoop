package WorkAoutSpark.Main20220605;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Tuple5;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransCsvTwoToDbeaver {
    public static void main(String[] args) throws Exception{
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("TransData", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset2 = spark.read().format("csv").option("sep", "\t")
                .load("file:\\D:\\googledownload\\loc-brightkite_totalCheckins.txt\\Brightkite_totalCheckins.csv");
        JavaRDD<Row> javaRDD = dataset2.toJavaRDD();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JavaRDD<Tuple5<Integer, Timestamp, Double, Double, String>> maprdd = javaRDD.map(new Function<Row, Tuple5<Integer, Timestamp, Double, Double, String>>() {
            @Override
            public Tuple5<Integer, Timestamp, Double, Double, String> call(Row v1) throws Exception {
                String s = v1.toString();
                String substring = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                String[] strings = substring.split(",");
                boolean b1 = strings[0] != "" && strings[1] != "" && strings[2] != "" && strings[3] != "" && strings[4] != "";
                boolean b2 = strings[0] != null && strings[1] != null && strings[2] != null && strings[3] != null && strings[4] != null;
                if (b1 && b2){
                    Integer user = Integer.valueOf(String.valueOf(strings[0]));
                    String date = strings[1].replace("T", " ").replace("Z", "");
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Double lat = Double.valueOf(strings[2]);
                    Double lon = Double.valueOf(strings[3]);
                    String location = String.valueOf(strings[4]);
                    Date date1 = sf.parse(date);
                    Timestamp timestamp = new Timestamp(date1.getTime());
                    return new Tuple5<>(user, timestamp, lat, lon, location);
                }else {
                    return new Tuple5<>(null, null, null, null, null);
                }
            }
        });
        JavaRDD<Tuple5<Integer, Timestamp, Double, Double, String>> filter = maprdd.filter(new Function<Tuple5<Integer, Timestamp, Double, Double, String>, Boolean>() {
            @Override
            public Boolean call(Tuple5<Integer, Timestamp, Double, Double, String> v1) throws Exception {
                boolean b1 = v1._1() != null;
                boolean b2 = !(v1._2().equals(null));
                boolean b3 = v1._3() != null;
                boolean b4 = v1._4() != null;
                boolean b5 = v1._5() != null;
                return b1 && b2 && b3 && b4 && b5;
            }
        });
        JavaRDD<Row> rdd = filter.map(new Function<Tuple5<Integer, Timestamp, Double, Double, String>, Row>() {
            @Override
            public Row call(Tuple5<Integer, Timestamp, Double, Double, String> v1) throws Exception {
                Row row = RowFactory.create(v1._1(),v1._2(),v1._3(),v1._4(),v1._5());
                return row;
            }
        });
        rdd.take(3).forEach(x -> System.out.println(x));
        Dataset<Row> dataFrame = spark.createDataFrame(rdd, StructType.fromDDL("`user` INT,`time` Timestamp,`latitude` DOUBLE,`longitude` DOUBLE,`location` STRING"));
        dataFrame.show(10);
//        con.PushToMySql(dataFrame,"Brightkite_totalCheckins","append");

    }
}
