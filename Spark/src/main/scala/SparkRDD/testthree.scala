package SparkRDD

import org.apache.spark.sql.SparkSession

object testthree extends App {
  val spark = SparkSession.builder.
    config("spark.ui.showConsoleProgress","false").
    config("spark.driver.host","192.168.1.4").
    appName("app").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  val rdd_1_1 = sc.parallelize(List("A","B","C","A"))
  val rdd_6 = sc.parallelize(List("A","E","C","S"))
  rdd_1_1.intersection(rdd_6).collect().foreach(x=>println(x))
//  spark.stop()
  println("over!!")
}
