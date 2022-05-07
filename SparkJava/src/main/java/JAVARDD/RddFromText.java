package JAVARDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RddFromText {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("RddFromText").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> rdd = jsc.textFile("file:\\F:\\CodeG50\\BiGData\\Spark\\src\\main\\resources\\DataSets\\Vegetable-carrots.txt");
        rdd.foreach(x -> System.out.println(x));
        System.out.println(rdd.getNumPartitions());
    }
}
