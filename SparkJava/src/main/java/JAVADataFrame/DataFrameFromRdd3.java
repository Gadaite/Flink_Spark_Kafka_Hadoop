package JAVADataFrame;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

public class DataFrameFromRdd3 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("DataFrameFromRdd3").master("local[*]").getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.createDataFrame(
                jsc.parallelize(Arrays.asList(RowFactory.create(1,2,3))),
                StructType.fromDDL("col1 Integer,col2 Integer,col3 Integer")
        );
        dataset.show();
        dataset.printSchema();

    }
}
