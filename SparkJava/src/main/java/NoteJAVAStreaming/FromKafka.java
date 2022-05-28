package NoteJAVAStreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import java.util.*;

public class FromKafka {
    public static void main(String[] args) throws Exception{
        SparkConf conf = new SparkConf().setAppName("FromKafka").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaStreamingContext jssc = new JavaStreamingContext(jsc, Durations.seconds(10));
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sparkapp",1);
        JavaPairReceiverInputDStream<String, String> data = KafkaUtils.createStream(
                jssc, "192.168.1.10:2181", "consumer-group", map
        );
        data.foreachRDD(
                x ->x.foreach(y -> System.out.println(y))
        );
        jssc.start();
        jssc.awaitTermination();
        jssc.stop();
    }
}
