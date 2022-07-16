package WorkAoutSpark2.Main20220716;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.Properties;


public class MovieOnline {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("MovieOnline", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<String> rdd = jsc.textFile("file:\\C:\\Users\\Gadaite\\Desktop\\MovieOnline.txt");
        JavaRDD<Row> resrdd = rdd.map(new Function<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> call(String v1) throws Exception {
                String[] strs = v1.split("：");
                return new Tuple2<>(strs[0], strs[1]);
            }
        }).map(new Function<Tuple2<String, String>, Row>() {
            @Override
            public Row call(Tuple2<String, String> v1) throws Exception {
                Row row = RowFactory.create(v1._1, v1._2);
                return row;
            }
        });
        Dataset<Row> dataFrame = spark.createDataFrame(resrdd, StructType.fromDDL("name String,url String"));
        dataFrame.show();
        Properties properties = con.getProperties();
        String replace = properties.getProperty("url").replace("CETC10S", "Gadaite");
        properties.setProperty("url",replace);
        con.setProperties(properties);
        con.PushToMySql(dataFrame,"MovieOnline","overwrite");
    }
}
