package WorkAoutSpark.Main20220528;

import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 比较Spark连接和自定义的JavaSql连接的效率
 * 输出：
 * Spark创建环境时长: 2456ms
 * Spark响应数据时长: 1894ms
 * JavaSql连接时长: 1ms
 * JavaSql响应时长: 7058ms
 * HashMap响应时长: 4981ms
 */
public class ComparSparkAndJavaSqlConnectDB extends MysqlJdbcCon {
    public static void main(String[] args) throws Exception {
        long time1 = System.currentTimeMillis();
        MysqlJdbcCon con = new MysqlJdbcCon();
        con.init();
        SparkSession spark = con.getSparkSesssion("HandleTable", "INFO");
        long time2 = System.currentTimeMillis();
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from flights limit 1000000");
        long time3 = System.currentTimeMillis();
        MysqlConnect connect = new MysqlConnect();
        long time4 = System.currentTimeMillis();
        ResultSet resultSet = connect.MysqlQuery("select * from `CETC10S`.`flights` limit 1000000");
        long time5 = System.currentTimeMillis();
        List<Map> maps = connect.GetREsultSetMapData(resultSet);
        long time6 = System.currentTimeMillis();
        System.out.println("Spark创建环境时长: " + (time2 - time1) + "ms");
        System.out.println("Spark响应数据时长: " + (time3 - time2) + "ms");
        System.out.println("JavaSql连接时长: " + (time4 - time3) + "ms");
        System.out.println("JavaSql响应时长: " + (time5 - time4) + "ms");
        System.out.println("HashMap响应时长: " + (time6 - time5) + "ms");
    }
}
