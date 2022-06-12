//package SparkJson
//
//
//import com.google.gson.JsonParser
//import org.apache.spark.sql.SparkSession
//object json_google extends App{
//    override def main(args: Array[String]): Unit = {
//        val spark = SparkSession.builder.appName("app").master("local[*]").getOrCreate()
//        val sc = spark.sparkContext
//        println("-----"*20)
//
//        //从json文件转换成json字符串
//        val jsonfile_rdd = sc.textFile("file:\\D:\\googledownload\\china.json")
//        val jsonfile_rdd_rows = jsonfile_rdd.collect().length
//        val json_str = jsonfile_rdd.collect().reduce((x,y) =>x+y).split(" ").reduce((x,y) =>x + " "+y)
//        println(json_str)
//        println("-----"*20)
//
//        //解析json字符串中的内容
//        val parser = new JsonParser()
//        val jread = parser.parse(json_str).getAsJsonObject()
//        val array = jread.get("features").getAsJsonArray()
//        val len = array.size()
//        for (i<-0 to(len -1)){
//            val dataRow = array.get(i).getAsJsonObject().get("properties").getAsJsonObject()
//            array.get(i).getAsJsonObject().get()
//            val adcode = dataRow.get("adcode").getAsString()
//            val name = dataRow.get("name").getAsString()
//            val center = dataRow.get("center").getAsString()
//            val centroid = dataRow.get("centroid").getAsString()
//            val childrenNum = dataRow.get("childrenNum").getAsString()
//            val level = dataRow.get("level").getAsString()
//            val parentCenter = dataRow.get("parent").getAsJsonObject().get("adcode").getAsString()
//            val subFeatureIndex = dataRow.get("subFeatureIndex").getAsString()
//            val acroutes = dataRow.get("acroutes").getAsString()
//            dataRow.get()
//
//            val value = dataRow.getClass()
//        }
//        val bicycle_color = jread.get("Features").getAsJsonObject().get("bicycle").getAsJsonObject().get("color").getAsString()
//        println(bicycle_color)//red
//        println("-----"*20)
//
//        val book_isbn = jread.get("store").getAsJsonObject().get("book").getAsJsonArray().get(1).getAsJsonObject().get("isbn").getAsString()
//        println(book_isbn)
//        println("-----"*20)
//
//        val book_price = jread.get("store").getAsJsonObject().get("book").getAsJsonArray().get(0).getAsJsonObject().get("price").getAsString()
//        println(book_price)
//        println("-----"*20)
//    }
//}