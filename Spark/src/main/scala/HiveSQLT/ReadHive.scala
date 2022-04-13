package HiveSQLT

import org.apache.spark.sql.SparkSession

object ReadHive extends App {
  val spark = SparkSession.builder().master("local[*]").appName("readhive").enableHiveSupport().getOrCreate()
  spark.sql("show databases").show()
  val HDF = spark.sql("select * from `hive_test_one`.`seeds_dataset`")
  HDF.show()
  HDF.printSchema()
}
