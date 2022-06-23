package WorkAoutSpark.Main20220623;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import scala.Tuple2;

/**
 * 16进制geometry数据与geometry的相互转换
 */
public class MakeSql {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("makersql", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.locationtech.geomesa.spark.GeoMesaSparkKryoRegistrator");
        spark.conf().set("geotools", "true");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from objecttrajactory limit 5");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        javaRDD.take(5).forEach(x -> System.out.println(x));
        GeometryFactory geometryFactory = new GeometryFactory();
        JavaRDD<Tuple2<Integer, Geometry>> transToGeometry = javaRDD.map(new RowToJavaBean<Objecttrajactory>(new GetDDL().GetGadaiteDDL(dataset), Objecttrajactory.class))
                .map(new Function<Objecttrajactory, Tuple2<Integer, Geometry>>() {
                    @Override
                    public Tuple2<Integer, Geometry> call(Objecttrajactory v1) throws Exception {
                        Integer lastappeared_id = v1.getLastappeared_id();
                        Geometry gps_line = new WKBReader().read(WKBReader.hexToBytes(v1.getGps_line()));
                        return new Tuple2<Integer, Geometry>(lastappeared_id, gps_line);
                    }
                });
        transToGeometry.take(5).forEach(x -> System.out.println(x));
        JavaRDD<Tuple2<Integer, String>> transFromGeometry = transToGeometry.map(new Function<Tuple2<Integer, Geometry>, Tuple2<Integer, String>>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<Integer, Geometry> v1) throws Exception {
                Geometry geometry = v1._2;
                byte[] write = new WKBWriter().write(geometry);
                String hex = WKBWriter.toHex(write);
                return new Tuple2<>(v1._1, hex);
            }
        });
        transFromGeometry.take(5).forEach(x -> System.out.println(x));
        JavaRDD<Tuple2<Integer, Geometry>> trans_transFromGeometry_ToGeometry = transFromGeometry.map(new Function<Tuple2<Integer, String>, Tuple2<Integer, Geometry>>() {
            @Override
            public Tuple2<Integer, Geometry> call(Tuple2<Integer, String> v1) throws Exception {
                Geometry geometry = new WKBReader().read(WKBReader.hexToBytes(v1._2));
                return new Tuple2<>(v1._1, geometry);
            }
        });
        trans_transFromGeometry_ToGeometry.take(5).forEach(x -> System.out.println(x));
    }
}
