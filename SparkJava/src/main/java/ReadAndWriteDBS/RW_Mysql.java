package ReadAndWriteDBS;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class RW_Mysql {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.uii.showConsoleProgresss", "false")
                .appName("rwmysql").master("local[*]").enableHiveSupport().getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.read().format("jdbc")
                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("url", "jdbc:mysql://192.168.1.10:3306/Gadaite")
                .option("user", "root")
                .option("password", "LYP809834049")
                .option("dbtable", "audi")
                .load();
        dataset.show();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "LYP809834049");
        dataset.write().mode(SaveMode.Overwrite).jdbc("jdbc:mysql://192.168.1.10:3306/Gadaite","result",properties);
    }
}
