package SparkMl

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Filter extends App{
    override def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("app").setMaster("local[*]")
        val sc = new SparkContext(conf)
        val rdd = sc.parallelize(Array(1,2,3,4,5,6,7,8,9))
        rdd.filter(x =>x%2==0).foreach(x =>print(x.toString()+" "))
        //2 4 6 8 
        sc.stop()
    }
}