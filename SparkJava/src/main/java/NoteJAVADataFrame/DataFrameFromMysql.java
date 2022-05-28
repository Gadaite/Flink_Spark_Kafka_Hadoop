package NoteJAVADataFrame;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameFromMysql {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameFromMysql")
                .master("local[*]").enableHiveSupport().getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");
        Dataset<Row> mysqlds = spark.read().format("jdbc")
                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("url", "jdbc:mysql://192.168.1.10/Gadaite")
                .option("user", "root")
                .option("password", "LYP809834049")
                .option("dbtable", "audi")
                .load();
        mysqlds.printSchema();
        mysqlds.show(10);
    }
}
