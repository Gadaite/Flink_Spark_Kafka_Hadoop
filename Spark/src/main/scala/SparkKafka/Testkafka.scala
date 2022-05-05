package SparkKafka

import org.apache.spark.sql.SparkSession
object Testkafka extends App {
  val spark = SparkSession.builder().config("spark.ui.showConsoleProgress","false")
    .appName("kafkatest").master("local[*]").enableHiveSupport().getOrCreate()
  val sc = spark.sparkContext
  sc.setLogLevel("ERROR")
  val df = spark.readStream.format("kafka")
  //  代码未完成
}
