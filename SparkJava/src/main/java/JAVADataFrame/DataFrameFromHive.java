package JAVADataFrame;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameFromHive {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameFromHive")
                .master("local[*]").enableHiveSupport().getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");
        spark.sql("show databases").show();
        spark.sql("use hive_test_one");
        Dataset<Row> sqldf = spark.sql("select * from audi limit 10");
        sqldf.show();
        sqldf.printSchema();
    }
}
