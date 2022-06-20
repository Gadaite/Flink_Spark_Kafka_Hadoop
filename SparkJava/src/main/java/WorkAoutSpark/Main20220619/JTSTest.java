package WorkAoutSpark.Main20220619;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.directory.shared.kerberos.codec.types.AuthorizationType;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geomgraph.GeometryGraph;
import scala.Tuple2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JTSTest {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220619\\BrightkiteTotalcheckins.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220619";
            autoCreateMysqlBean.generateTables = new String[]{"Brightkite_totalCheckins"};
            autoCreateMysqlBean.generate();
        }
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("JTS", "ERROR");
//        SparkSession.builder().appName("pp").master("local[*]").getOrCreate().
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from Brightkite_totalCheckins limit 1000");
        dataset.printSchema();
        dataset.show();
        Dataset<Row> filter = dataset.filter(new FilterFunction<Row>() {
            @Override
            public boolean call(Row value) throws Exception {
                return value.getInt(value.fieldIndex("user")) == 15297;
            }
        });
        JavaRDD<Row> javaRDD = filter.toJavaRDD();
        JavaRDD<BrightkiteTotalcheckins> maprdd = javaRDD.
                map(new RowToJavaBean<BrightkiteTotalcheckins>(new GetDDL().GetGadaiteDDL(filter), BrightkiteTotalcheckins.class));
        JavaRDD<Iterable<BrightkiteTotalcheckins>> line = maprdd.mapToPair(new PairFunction<BrightkiteTotalcheckins, Integer, BrightkiteTotalcheckins>() {
            @Override
            public Tuple2<Integer, BrightkiteTotalcheckins> call(BrightkiteTotalcheckins v1) throws Exception {
                return new Tuple2<>(v1.getUser(), v1);
            }
        }).groupByKey().map(new Function<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>, Iterable<BrightkiteTotalcheckins>>() {
            @Override
            public Iterable<BrightkiteTotalcheckins> call(Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> v1) throws Exception {
                ArrayList<BrightkiteTotalcheckins> list = new ArrayList<>();
                v1._2.forEach(x -> {
                    list.add(x);
                });
                list.sort(Comparator.comparing(BrightkiteTotalcheckins::getTime));
                return list;
            }
        });
        line.map(new Function<Iterable<BrightkiteTotalcheckins>, LineString>() {
            @Override
            public LineString call(Iterable<BrightkiteTotalcheckins> brightkiteTotalcheckins) throws Exception {
                List<Coordinate> coordinates = new ArrayList<>();
                brightkiteTotalcheckins.forEach(x ->{
                    Coordinate coordinate = new Coordinate(x.getLongitude(), x.getLatitude());
                    coordinates.add(coordinate);
                });
                GeometryFactory geometryFactory = new GeometryFactory();
                LineString lineStr = geometryFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
                return lineStr;
            }
        }).foreach(x -> System.out.println(x.getLength()));

        line.map(new Function<Iterable<BrightkiteTotalcheckins>, LineString>() {
            @Override
            public LineString call(Iterable<BrightkiteTotalcheckins> brightkiteTotalcheckins) throws Exception {
                List<Coordinate> coordinates = new ArrayList<>();
                brightkiteTotalcheckins.forEach(x ->{
                    Coordinate coordinate = new Coordinate(x.getLongitude(), x.getLatitude());
                    coordinates.add(coordinate);
                });
                GeometryFactory geometryFactory = new GeometryFactory();
                LineString lineStr = geometryFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
                return lineStr;
            }
        }).foreach(x -> System.out.println(x.getLength()));

    }
}
