package JavaDataFrame;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameLocalCSV {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameOne")
                .master("local[*]").enableHiveSupport().getOrCreate();
        Dataset<Row> csvdataset = spark.read().format("csv").option("header","true").option("inferschema","true")
                .load("file:\\F:\\CodeG50\\BiGData\\Spark\\src\\main\\resources\\DataSets\\seeds_dataset.csv");
        csvdataset.show();
        csvdataset.printSchema();
    }
}
