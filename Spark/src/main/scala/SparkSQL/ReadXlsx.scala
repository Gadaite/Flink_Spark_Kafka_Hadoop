package SparkSQL

import org.apache.spark.sql.SparkSession

object ReadXlsx {
  val spark = SparkSession.builder.appName("APP").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  val inputdf = spark.read.format("excel")

}
