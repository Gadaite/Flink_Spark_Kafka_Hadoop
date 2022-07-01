package WorkAoutSpark2.Main20220701;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.sql.SparkSession;

public class GeomesaTestOne {
    public static void main(String[] args) throws Exception{
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("Geomesa", "ERROR");
        spark.sql("show databases").show();
    }
}
