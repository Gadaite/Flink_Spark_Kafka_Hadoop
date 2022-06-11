package WorkAoutSpark.Main20220611;

import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import GadaiteToolGeo.BinaryToGeometry;
import GadaiteToolGeo.StrLineSTRING;
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

public class PSqlTest {
    public static void main(String[] args) throws Exception {

        /**
         * 测试读取
         */
        //todo : 数据读取和dbeaver上看到的不同
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
        map.take(1).forEach(x -> System.out.println(x));

        /**
         * 测试上传
         */
        PostgresqlConnect connect = new PostgresqlConnect();
        ResultSet resultSet = connect.PSqlQuery("select * from objecttrajactory limit 10");
        System.out.println(resultSet.getMetaData().getColumnTypeName(2));// 输出：geometry
        List<Map> maps = connect.GetREsultSetMapData(resultSet);
    }
}
