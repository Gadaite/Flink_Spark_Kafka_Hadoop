package ZTestOnlY;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.SparkSession;

public class Test05 {
    public static void main(String[] args) {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("tt", "ERROR");
        spark.sql("show databases").show();
    }
}
