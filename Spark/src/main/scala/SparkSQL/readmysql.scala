package SparkSQL
//
import java.util.Properties

import org.apache.spark.sql.SparkSession

object readmysql extends App {
  val spark = SparkSession.builder().appName("app").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  sc.setLogLevel("ERROR")

//  val jdbcdf = spark.read.format("jdbc")
//    .option("driver","com.mysql.cj.jdbc.Driver")
//    .option("url","jdbc:mysql://192.168.1.10:3306/Gadaite")
//    .option("user","root")
//    .option("password","LYP809834049")
//    .option("dbtable","audi")
//    .load()
  val url =  "jdbc:mysql://192.168.1.10:3306/Gadaite?useUnicode=true&characterEncoding=utf-8"
  val prop = new Properties()
  prop.setProperty("user","root")
  prop.setProperty("password","LYP809834049")
  val df = spark.read.jdbc(url,"audi",prop)
  df.show(3)//方式一，直接读取整个表下来
//  df.filter()

  //方式二注册成临时表使用sql查询
  df.createOrReplaceTempView("tmp")
  spark.sql("select * from tmp limit 1 ").show()

  spark.sql("use hive_test_one")
  spark.sql("show tables").show()

//  df.show()

}
