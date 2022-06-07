package WorkAoutSpark.Main20220607;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.functions;

/**
 * made by Gadaite
 */
public class UdfTest {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("UdfTest", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from audi limit 10");
        spark.sqlContext().udf().register("UDF",new UDFFunction(), DataTypes.IntegerType);
        /**
         * 临时表view中使用udf操作
         */
        dataset.createOrReplaceTempView("audi");
        spark.sql("select *,UDF(year) as udf from audi ").show(2);
        /**
         * 直接对Dataset进行udf操作
         */
        dataset.withColumn("udf2", functions.callUDF("UDF",functions.col("price"))).show(2);
    }
}
