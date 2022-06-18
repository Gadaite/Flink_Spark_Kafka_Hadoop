package WorkAoutSpark.Main20220619;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.zip.CRC32;

/**
 * 相关计算需要放在特定的坐标系下才有意义
 * 一般只是提供基础的欧几里得空间计算
 */

public class PointsDistance {
    public static void main(String[] args) {
        /**
         * 为三维直角坐标系下距离：5
         */
        Coordinate coordinate1 = new Coordinate(0, 3, 0);
        Coordinate coordinate2 = new Coordinate(4, 0, 0);
        System.out.println(coordinate1.distance3D(coordinate2));
        /**
         * 输出结果还是5，应该还是在三维直角坐标系下进行计算的
         */
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point1 = geometryFactory.createPoint(coordinate1);
        Point point2 = geometryFactory.createPoint(coordinate2);
        System.out.println(point1.distance(point2));
        /**
         * 使用Gis进行计算结果50，感觉还是在直角坐标系下进行计算的
         */
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("GIS", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.apache.sedona.core.serde.SedonaKryoRegistrator");
        SedonaSQLRegistrator.registerAll(spark);
        Dataset<Row> dspoint = spark.sql("select ST_Distance(ST_point(0.0,30.0),ST_Point(40.0,0.0)) as distance");
        dspoint.show();


    }
}
