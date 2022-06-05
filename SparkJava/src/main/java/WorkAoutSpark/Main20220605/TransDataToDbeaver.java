package WorkAoutSpark.Main20220605;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class TransDataToDbeaver {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("Trandata", "ERROR");
        Dataset<Row> csv = spark.read().format("csv").option("header","true").load("file:\\D:\\googledownload\\foursquare-location-matching\\train.csv");
        csv.printSchema();
        csv.show(10);
        try {
            Properties prop = new Properties();
            prop.put("user", con.init().getProperty("username"));
            prop.put("password", con.init().getProperty("pwd"));
            csv.write().mode(SaveMode.Append).jdbc(con.init().getProperty("url"),"T_train",prop);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
