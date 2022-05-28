package NoteJAVARDD;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class WordCount {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName("wordcount").master("local[*]").enableHiveSupport().getOrCreate();
        SparkContext sc = spark.sparkContext();
        sc.setLogLevel("ERROR");
        SparkConf conf = sc.getConf();
        spark.stop();
        sc.stop();
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> rdd = jsc.parallelize(Arrays.asList("spark,spark,hadoop,flink,hive,hbase,kafka,kafka"));
        rdd.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                String[] split = s.split(",");
                return Arrays.asList(split).iterator();
            }
        }).mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        }).foreach(x -> System.out.println(x));

    }
}
