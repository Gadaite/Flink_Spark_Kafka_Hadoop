package JAVADataFrame;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameHDFS {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameHDFS")
                .master("local[*]").enableHiveSupport().getOrCreate();
        Dataset<Row> hdfscsv = spark.read().format("csv").option("header","true").option("inferschema","true")
                .load("hdfs://192.168.1.10:9000/HadoopFileS/DataSet/MLdataset/adult.csv");
        hdfscsv.printSchema();
        hdfscsv.show(10);
    }
}
