package SparkRDD
import org.apache.spark.sql.SparkSession
import scala.util.Properties
object wordcount extends App{
    override def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder.config("spark.ui.showConsoleProgress","false")
          .appName("myapp").master("local[*]").getOrCreate()
        val sc = spark.sparkContext
        Properties.setProp("scala.time","true")
        val rdd_words = sc.textFile("hdfs://192.168.1.10:9000//HadoopFileS/DataSet/Others/My_Internship_Experience.txt")
        val result = rdd_words.flatMap(lines =>lines.split(" "))
                .map(word =>(word,1)).reduceByKey((x,y) => x+y).sortBy(_._2)
        result.foreach(println)
        Properties.setProp("scala.time","true")
        sc.stop()
        spark.stop()
    }
}