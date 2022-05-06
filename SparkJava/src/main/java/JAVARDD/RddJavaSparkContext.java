package JAVARDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class RddJavaSparkContext {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("JavaSparkContext").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<Integer> data = jsc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        data.foreach(x -> System.out.println(x));
    }
}
