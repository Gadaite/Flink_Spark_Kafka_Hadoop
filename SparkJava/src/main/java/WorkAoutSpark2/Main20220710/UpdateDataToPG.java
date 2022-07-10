package WorkAoutSpark2.Main20220710;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class UpdateDataToPG {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("update", "ERROR");
        Dataset<Row> ds = spark.read().option("header",true).csv("file:\\D:\\googledownload\\archive (4)\\train.csv");
        pcon.PushToPSql(ds,"taxitrajectorydata","overwrite");

    }
}
