//package HbaseSpark
//
//import org.apache.hadoop.hbase.HBaseConfiguration
//import org.apache.hadoop.hbase.mapreduce.TableInputFormat
//import org.apache.hadoop.hbase.util.Bytes
//import org.apache.spark.{SparkConf, SparkContext}
//object readhbase{
//  def main(args: Array[String]) {
//    val conf = HBaseConfiguration.create()
//    val sc = new SparkContext(new SparkConf())
//    //设置查询的表名
//    conf.set(TableInputFormat.INPUT_TABLE, "student")
//    val stuRDD = sc.newAPIHadoopRDD(conf, classOf[TableInputFormat],
//      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
//      classOf[org.apache.hadoop.hbase.client.Result])
//    val count = stuRDD.count()
//    println("Students RDD Count:" + count)
//    stuRDD.cache()
//
//    //遍历输出
//    stuRDD.foreach({ case (_,result) =>
//      val key = Bytes.toString(result.getRow)
//      val name = Bytes.toString(result.getValue("info".getBytes,"name".getBytes))
//      val gender = Bytes.toString(result.getValue("info".getBytes,"gender".getBytes))
//      val age = Bytes.toString(result.getValue("info".getBytes,"age".getBytes))
//      println("Row key:"+key+" Name:"+name+" Gender:"+gender+" Age:"+age)
//    })
//
//  }
//}
//
