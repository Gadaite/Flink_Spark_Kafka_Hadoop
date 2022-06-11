package WorkAoutSpark.Main20220612;

import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import GadaiteToolGeo.BinaryToGeometry;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 测试PostgresqlJdbcCon方式读取PSql
 * 完成
 */
public class PSqlJdbcReadTest {
    public static void main(String[] args) throws Exception {

        /**
         * 测试读取
         */
        //todo : 数据读取和dbeaver上看到的不同，为二进制数据(已解决如下)
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("test", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from objecttrajactory limit 10");
        dataset.select("gps_line").show(3);
        dataset.printSchema();
        JavaRDD<Row> javaRDD = dataset.select("gps_line").toJavaRDD();
        JavaRDD<Geometry> map = javaRDD.map(new Function<Row, Geometry>() {
            @Override
            public Geometry call(Row v1) throws Exception {
                String s = v1.toString();
                LineString lineString = new BinaryToGeometry().JTSLineString(s);
                return lineString;
            }
        });
        map.take(10).forEach(x -> System.out.println(x));
    }
}
