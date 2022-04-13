package SparkSQL

import org.apache.spark.sql.{DataFrame, SparkSession}

object mysql_connect extends App{
    override def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder().appName("app").master("local[*]").getOrCreate()

        def findtable(table:String):DataFrame = {

            val inputdf = spark.read.format("jdbc")
                .option("driver","com.mysql.cj.jdbc.Driver")
                .option("url","jdbc:mysql://192.168.1.10/Gadaite")
                .option("user","root")
                .option("password","LYP809834049")
                .option("dbtable",s"$table")
                .load()
            return inputdf
        }
        findtable("Customers").createOrReplaceTempView("Customers")
        findtable("Orders").createOrReplaceTempView("Orders")
        findtable("Products").createOrReplaceTempView("Products")
        findtable("OrderItems").createOrReplaceTempView("OrderItems")
        findtable("Vendors").createOrReplaceTempView("Vendors")

        spark.sql("""
            SELECT order_num
            FROM OrderItems
            WHERE prod_id = 'RGAN01'
            """).show()

        spark.sql("""
            SELECT cust_id
            FROM Orders
            WHERE order_num IN (20007,20008)
            """).show()

        spark.sql("""
            SELECT cust_id
            FROM Orders
            WHERE order_num IN (SELECT order_num
            FROM OrderItems
            WHERE prod_id = 'RGAN01')
            """).show()

        spark.sql("""
            SELECT vend_name, prod_name, prod_price
            FROM Vendors, Products
            WHERE Vendors.vend_id = Products.vend_id 
            """).show()

        
    }
}