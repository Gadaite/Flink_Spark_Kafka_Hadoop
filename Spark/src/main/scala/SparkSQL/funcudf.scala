package SparkSQL
import org.apache.spark.sql.types.{DoubleType, StringType}
import org.apache.spark.sql.{SparkSession, functions}

object funcudf extends App {
  val spark = SparkSession.builder.appName("APP").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  val inputdf = spark.read.format("jdbc")
    .option("driver","com.mysql.cj.jdbc.Driver")
    .option("url","jdbc:mysql://139.155.70.177/Gadaite")
    .option("user","root")
    .option("password","zzjz123")
    .option("dbtable","iris_data")
    .load()
  inputdf.show()
  def func1(x:Any,y:Any):Double ={
    return x.toString().toDouble * y.toString().toDouble
  }
  // import spark.implicits
  // import spark.sqlContext
  val func1_udf = functions.udf(func1(_:Any,_:Any),DoubleType)
  val func1_df1 = inputdf.withColumn("sepal-area",func1_udf(inputdf.col("sepal-length"),inputdf.col("sepal-width")))
  func1_df1.show()
  func1_df1.printSchema()
  val func1_df2 = func1_df1.withColumn("petal-area",func1_udf(func1_df1.col("petal-length"),func1_df1.col(" petal-width")))
  func1_df2.show()

  val func3 =(x:Any,y:Any) =>{
    val sums = 2*(x.toString.toDouble) + 2*(y.toString.toDouble)
    sums.toDouble.toString()
  }
  val func3_udf = functions.udf(func3,StringType)

  /**     以下定义也可以实现
   *     val func3_udf = functions.udf(func3(_:Any,_:Any),StringType)
   */

  val func3_df3 = inputdf.withColumn("sepal-range",func3_udf(inputdf.col("sepal-length"),inputdf.col("sepal-width")))
  func3_df3.show()
  spark.stop()
  sc.stop()

}
