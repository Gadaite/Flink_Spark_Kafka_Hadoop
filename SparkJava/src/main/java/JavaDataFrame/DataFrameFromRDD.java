package JavaDataFrame;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public class DataFrameFromRDD {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameFromRDD")
                .master("local[*]").enableHiveSupport().getOrCreate();
        SQLContext sqlContext = spark.sqlContext();

    }
}
