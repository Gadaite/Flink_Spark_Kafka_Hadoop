package SparkMl

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object FlatMap extends App{
    override def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("app").setMaster("local[*]")
        val sc = new SparkContext(conf)
        var arr = sc.parallelize(Seq(Array(1, 2, 3, 4, 5),Array(11,12,13,14,15))) //创建数据集
        val result = arr.flatMap(x=>x).collect() //进行数据集计算
        result.foreach(println) //打印结果
        /**
          * 2
            3
            4
            5
            6
          */
    }
}