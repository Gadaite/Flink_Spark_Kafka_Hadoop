package JAVADataFrame;

import JavaBean.UDFOneSchema;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    下面代码执行报错
 */
public class DataFrameFromUdfschema {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","F:\\HadoopWin");
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("udfsschema").master("local[*]").enableHiveSupport().getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<Row> javaRDD = jsc.parallelize(Arrays.asList(
                RowFactory.create(1, 10, "zhangsan"),
                RowFactory.create(2, 20, "lisi"),
                RowFactory.create(3, 30, "wangwu")
        ));
        JavaRDD<List<? extends Serializable>> javaRDD1 = jsc.parallelize(Arrays.asList(
                Arrays.asList(1, 10, "zhangsan"),
                Arrays.asList(2, 20, "lisi"),
                Arrays.asList(3, 30, "wangwu")
        ));
        UDFOneSchema udfOneSchema = new UDFOneSchema();
        javaRDD1.foreach(x -> System.out.println(x));
        javaRDD1.map(new Function<List<? extends Serializable>, UDFOneSchema>() {
        })
//        Dataset<Row> dataFrame = spark.createDataFrame(javaRDD, UDFOneSchema.class);
//        dataFrame.show();
    }
}
