package SparkZzjz.arraydense

import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession
object ArrayToVectors {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.config("spark.Driver.host", "192.168.1.10")
      .config("spark.ui.showConsoleProgress", "false").appName("").master("local[*]")
      .getOrCreate()
    val sc = spark.sparkContext
    val rdds = sc.textFile("hdfs://192.168.1.10:9000/HadoopFileS/DataSet/Others/bank_train.csv")
    println(rdds.getNumPartitions)
//    val df = spark.createDataFrame(rdds,schema)
    val v1 = Vectors.dense(Array(1.0, 2.0))
    val v2 = v1.toArray
    println(v2.getClass())
    Thread.sleep(100000000)
  }
}
