package JAVARDD;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class RangeToRdd {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("RddOne").master("local[*]").enableHiveSupport().getOrCreate();
        SparkContext sc = spark.sparkContext();
        sc.setLogLevel("ERROR");
        Dataset<Long> data = spark.range(1, 10);
        spark.sql("use hive_test_one");
        spark.sql("select * from audi limit 10").show();
        spark.sql("show databases").show();
        data.show();

//        JavaSpark
    }
}
