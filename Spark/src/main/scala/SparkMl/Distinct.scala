package SparkMl

import org.apache.spark.sql.SparkSession

object Distinct extends App{
    override def main(args: Array[String]): Unit = {
//        val conf = new SparkConf().setAppName("app").setMaster("local[*]")
        val spark = SparkSession.builder.config("spark.Driver.host","192.168.1.10")
          .appName("Distinct").master("local[*]").getOrCreate()

//        val sc = new SparkContext(conf)
        val sc = spark.sparkContext
        val rdd = sc.parallelize(Array(("cool"), ("good"), ("bad"), ("fine"), ("good"), ("cool")))
        rdd.distinct().foreach(x =>print(x+" "))
        //cool bad good fine 

    }
}