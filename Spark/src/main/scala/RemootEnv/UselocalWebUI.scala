package RemootEnv

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
object UselocalWebUI {
  def main(args: Array[String]): Unit = {
    //程序在运行时receiver会独占一个线程,所以streaming程序至少要两个线程,防止starvation scenario
    val spark = SparkSession.builder().appName("StreamingWordCount")
      .config("spark.cores.max","2").master("spark://192.168.1.10:7077").getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")
    val conf = sc.getConf
    //所有流功能的主要入口
    val ssc: StreamingContext = new StreamingContext(sc , Seconds(5))
    val stream: ReceiverInputDStream[String] =  ssc.socketTextStream("192.168.1.10",7777)
    val dStream: DStream[(String, Int)] =  stream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)
    dStream.foreachRDD(x =>print(x))
    ssc.start()
    ssc.awaitTermination()
    //true    会把内部的sparkcontext同时停止
    //false  只会停止streamingcontext  不会停sparkcontext
//    ssc.stop(true)

  }



}
