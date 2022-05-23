package JAVADataFrame;

import JavaBean.JJM;
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
        JavaRDD<JJM> oneSchemaJavaRDD = jsc.parallelize(Arrays.asList(
                new JJM(1, 10, "one"),
                new JJM(2, 20, "two"),
                new JJM(3, 30, "three")
        ));
        oneSchemaJavaRDD.foreach(x -> System.out.println(x));
        Dataset<Row> dataFrame = spark.createDataFrame(oneSchemaJavaRDD, JJM.class);
        dataFrame.show();
        dataFrame.printSchema();
    }
}
