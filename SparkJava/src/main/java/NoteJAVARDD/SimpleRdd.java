package NoteJAVARDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class SimpleRdd {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("RddTwo")
                .master("local[*]").enableHiveSupport().getOrCreate();
        SparkConf conf = spark.sparkContext().getConf();
        spark.stop();
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<Integer> rdd = jsc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        jsc.setLogLevel("ERROR");
        rdd.foreach(x -> System.out.println(x));
    }
}
