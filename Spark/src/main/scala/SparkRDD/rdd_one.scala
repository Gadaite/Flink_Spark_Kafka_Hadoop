package SparkRDD

import org.apache.spark.sql.SparkSession
object rdd_one extends App {
    
    val spark = SparkSession.builder.config("spark.ui.showConsoleProgress","false")
      .config("spark.Driver.host","192.168.1.10")
      .appName("app").master("local[*]").getOrCreate()
    val sc = spark.sparkContext
    val arr = Array(1,2,3,4,5)
    val rdd = sc.parallelize(arr)
    val sum = rdd.reduce(_ + _)
    println(sum)
    print(rdd.getClass())
//    spark.stop()
    Thread.sleep(10000)
}
