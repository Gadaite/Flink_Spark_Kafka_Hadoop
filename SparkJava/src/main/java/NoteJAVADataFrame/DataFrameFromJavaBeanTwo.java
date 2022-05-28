package NoteJAVADataFrame;

import NoteJavaBean.UDFOneSchema;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class DataFrameFromJavaBeanTwo {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","F:\\HadoopWin");
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("udfsschema2").master("local[*]").enableHiveSupport().getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<UDFOneSchema> oneSchemaJavaRDD = jsc.parallelize(Arrays.asList(
                new UDFOneSchema(1, 10, "one"),
                new UDFOneSchema(2, 20, "two"),
                new UDFOneSchema(3, 30, "three")
        ));
        oneSchemaJavaRDD.foreach(x -> System.out.println(x));
        Dataset<Row> dataFrame = spark.createDataFrame(oneSchemaJavaRDD, UDFOneSchema.class);
        dataFrame.show();
        dataFrame.printSchema();
    }
}
