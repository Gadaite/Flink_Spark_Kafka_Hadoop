package WorkAoutSpark.Main20220621;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGeoDs {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("readpg", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<Row> rdd = jsc.parallelize(Arrays.asList(
                RowFactory.create(100.0, 50.0),
                RowFactory.create(110.0, 60.0)
        ));
        JavaRDD<Coordinate> coordinateJavaRDD = rdd.map(new Function<Row, Coordinate>() {
            @Override
            public Coordinate call(Row v1) throws Exception {
                double aDouble = v1.getDouble(0);
                double bDouble = v1.getDouble(1);
                Coordinate coordinate = new Coordinate(aDouble, bDouble);
                return coordinate;
            }
        });
        JavaRDD<Geometry> geometryJavaRDD = coordinateJavaRDD.mapToPair(new PairFunction<Coordinate, String, Coordinate>() {
            @Override
            public Tuple2<String, Coordinate> call(Coordinate coordinate) throws Exception {
                return new Tuple2<>("key", coordinate);
            }
        }).groupByKey().map(new Function<Tuple2<String, Iterable<Coordinate>>, Geometry>() {
            @Override
            public Geometry call(Tuple2<String, Iterable<Coordinate>> v1) throws Exception {
                List<Coordinate> list = new ArrayList<>();
                v1._2.forEach(x -> {
                    list.add(x);
                });
                GeometryFactory geometryFactory = new GeometryFactory();
                LineString lineString = geometryFactory.createLineString(list.toArray(new Coordinate[list.size()]));
                Geometry geometry = geometryFactory.createGeometry(lineString);
                return geometry;
            }
        });
        geometryJavaRDD.foreach(x -> System.out.println(x));
        Dataset<Geometry> geometryDataset = spark.createDataset(geometryJavaRDD.rdd(), Encoders.kryo(Geometry.class));
        geometryDataset.show();
        geometryDataset.printSchema();
        System.out.println(geometryDataset.schema().toString());
        geometryDataset.toJavaRDD().map(new Function<Geometry, Void>() {
            @Override
            public Void call(Geometry v1) throws Exception {
                System.out.println(v1.getClass().getName());
                return null;
            }
        }).take(1);

    }
}
