package JAVADataFrame;

import JavaBean.UDFOneSchema;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
/*
    beanclass反射创建SparkDataframe
 */
public class DataFrameFromJavaBeanOne {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","F:\\HadoopWin");
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("udfsschema").master("local[*]").enableHiveSupport().getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<String> javaRDD = jsc.parallelize(Arrays.asList(
                ("1,10,zhangsan"),
                ("2,20,lisi"),
                ("3,30,wangwu")
        ));
        JavaRDD<UDFOneSchema> objectJavaRDD = javaRDD.map(x -> {
            String[] strings = x.split(",");
            UDFOneSchema udfOneSchema = new UDFOneSchema();
            udfOneSchema.setNo(Integer.parseInt(strings[0]));
            udfOneSchema.setAge(Integer.parseInt(strings[1]));
            udfOneSchema.setName(String.valueOf(strings[2]));
            return udfOneSchema;
        });
        Dataset<Row> dataFrame = spark.createDataFrame(objectJavaRDD, UDFOneSchema.class);
        dataFrame.show();
    }
}
