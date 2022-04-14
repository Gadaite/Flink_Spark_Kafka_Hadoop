package SparkSQL

import org.apache.spark.sql.SparkSession

object readhdfs extends App {
  val spark = SparkSession.builder().appName("app").master("local[*]").getOrCreate()
  val jdbcdf = spark.read.format("jdbc")
    .option("driver","org.postgresql.Driver")
    .option("url","jdbc:postgresql://139.155.70.177:5432/trajectory")
    .option("dbtable","lastappeared")
    .option("user","postgres")
    .option("password","zzjz123")
    .load()
  jdbcdf.show()

}
