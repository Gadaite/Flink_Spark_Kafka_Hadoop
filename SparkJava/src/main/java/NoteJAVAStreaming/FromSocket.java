package NoteJAVAStreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class FromSocket {
    public static void main(String[] args) throws Exception{
        SparkConf conf = new SparkConf().setAppName("FromSocket").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaStreamingContext jssc = new JavaStreamingContext(jsc, Durations.seconds(10));
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("192.168.1.10", 7777);
        lines.foreachRDD(x ->x.foreach(y -> System.out.println(y)));
        jssc.start();
        jssc.awaitTermination();
        jssc.stop();
    }
}
