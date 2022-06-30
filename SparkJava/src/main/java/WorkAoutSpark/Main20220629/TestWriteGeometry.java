package WorkAoutSpark.Main20220629;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.JavaRowRddAddSchema;
import GadaiteToolConnectDB.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;

import java.sql.Timestamp;


public class TestWriteGeometry {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pj = new PostgresqlJdbcCon();
        SparkSession spark = pj.getSparkSesssion("testWriteToPostgresSql", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = pj.GetDataSetByProperties(spark, "select \"allDuration\",\"endPoint\",\"endtTime\",\"lineString\" from taxidataoforder limit 10");
        dataset.show();
        dataset.printSchema();
        //  Row
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        System.out.println(javaRDD.take(1).get(0).schema().simpleString());
        JavaRDD<Row> newRowRDD = javaRDD.map(new Function<Row, Row>() {
            @Override
            public Row call(Row v1) throws Exception {
                double allDuration = v1.getDouble(v1.fieldIndex("allDuration"));
                Geometry endPoint = new WKBReader().read(WKBReader.hexToBytes(v1.getString(v1.fieldIndex("endPoint"))));
                Geometry lineString = new WKBReader().read(WKBReader.hexToBytes(v1.getString(v1.fieldIndex("lineString"))));
                Timestamp endtTime = v1.getTimestamp(v1.fieldIndex("endtTime"));
                return RowFactory.create(allDuration, endPoint, endtTime, lineString);
            }
        }).map(new JavaRowRddAddSchema(new GetDDL().GetGadaiteDDL(dataset).replace("string", "geometry")));
        JavaRDD<EntityA> entityAJavaRDD = javaRDD.map(new Function<Row, EntityA>() {
            @Override
            public EntityA call(Row v1) throws Exception {
                double allDuration = v1.getDouble(v1.fieldIndex("allDuration"));
                Geometry endPoint = new WKBReader().read(WKBReader.hexToBytes(v1.getString(v1.fieldIndex("endPoint"))));
                Geometry lineString = new WKBReader().read(WKBReader.hexToBytes(v1.getString(v1.fieldIndex("lineString"))));
                Timestamp endtTime = v1.getTimestamp(v1.fieldIndex("endtTime"));
                return new EntityA(allDuration, endPoint, endtTime, lineString);
            }
        });
        JavaRDD<EntityA> cache = entityAJavaRDD.cache();
        cache.foreach(x -> System.out.println(x));
        //  建表
        new PostgresSqlEntityCreate().ExecCreateEntity(cache,"testA");
        new PostgresSqlEntityInsert().ExecInsertEntity(cache,"testA");
    }
}
