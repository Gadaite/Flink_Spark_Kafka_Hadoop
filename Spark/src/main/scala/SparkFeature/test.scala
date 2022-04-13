package SparkFeature

object test {
  import org.apache.spark.sql.SparkSession
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().config("spark.driver.host","192.168.1.5")
      .config("spark.ui.showConsoleProgress","false")
      .appName("test").master("local[*]").getOrCreate()
    val sc = spark.sparkContext
    val rdd = sc.parallelize(
      Seq(1,2,3,4,5,6)
    )
    println(rdd.getNumPartitions)
    println("------"*10)
    rdd.foreach(println)
//    rdd.foreach(x =>sc.parallelize(Array(x)))
//    Thread.sleep(10000)
  }

}
