package WorkAoutSpark2.Main20220701;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.sql.SparkSession;
import org.locationtech.geomesa.spark.GeoMesaSparkKryoRegistrator;

public class GeomesaTestOne {
    public static void main(String[] args) throws Exception{
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("Geomesa", "ERROR");
        spark.conf().set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator",GeomesaSpa );
    }
}
