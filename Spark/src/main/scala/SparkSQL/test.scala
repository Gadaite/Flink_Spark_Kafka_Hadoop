package SparkSQL

import org.apache.spark.sql.SparkSession

object test extends App {
  val spark = SparkSession.builder().appName("test").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  val rdd = sc.parallelize(Seq(1,2,3,4,5,6,7))
  rdd.foreach(println)
}
