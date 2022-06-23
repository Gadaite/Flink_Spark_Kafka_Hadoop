package WorkAoutSpark.Main20220623;

import GadaiteToolConnectDB.PostgresSqlMaker;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;
import java.util.Set;

public class MakeSql {
    public static void main(String[] args) throws Exception {
        PostgresqlConnect connect = new PostgresqlConnect();
        Map<String, String> nameMapType = connect.GetColumnNameMapType(connect.PSqlQuery("select * from objecttrajactory limit 1"));
        Set<Map.Entry<String, String>> entries = nameMapType.entrySet();
        for (Map.Entry<String, String> entry : entries){
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("makersql", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.locationtech.geomesa.spark.GeoMesaSparkKryoRegistrator");
        spark.conf().set("geotools", "true");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from objecttrajactory");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        new PostgresSqlMaker(connect).ExecInsert(javaRDD,"test20220622");
//        new PostgresSqlMaker(connect).ExecCreate(javaRDD,"test20220623");
//        Map<String, String> test20220622 = connect.GetColumnNameMapType("test20220622");
//        System.out.println("breakpoint");
    }
}
