package SparkDataframe


import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.types._
object renamedata extends App{
    override def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder().appName("app").master("local[*]").getOrCreate()
        val sc = spark.sparkContext
        val inputdata = Seq(
            (1,"xiaobao","Java"),
            (2,"yangyi","Scala"),
            (3,"liuzu","SQL")
        )
        //val schema = Array("NO","name","code")

        val inputdf = spark.createDataFrame(inputdata).toDF("NO","name","code")

        inputdf.show()

        def en2zh(x:String)={
            var y ="aaa"
            if(x.toString() == "Java"){
                y = "JAVA工程师".toString()
            }
            if(x.toString() == "Scala"){
                y = "大数据工程师".toString()
            }
            if(x.toString() == "SQL"){
                y = "数据库工程师".toString()
            }
            y
        }

        //注册udf函数
        val udf_en2zh = functions.udf(en2zh(_:String),StringType)

        //使用udf函数新增一列
        val temp_df = inputdf.withColumn("job",udf_en2zh(inputdf.col("code")))

        //注册临时表
        temp_df.createOrReplaceTempView("temp_table")
        
        temp_df.show()
        val outputdf = spark.sql("""
            select `No`,`name`,`job` from temp_table
            """)
        outputdf.show()
    }
}