package WorkAoutSpark.Main20220618;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import com.esotericsoftware.kryo.KryoSerializable;
import org.apache.sedona.sql.utils.GeometrySerializer;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.catalog.Function;
import org.apache.spark.sql.sedona_sql.expressions.ST_Area;
import org.apache.spark.sql.sedona_sql.expressions.ST_Point;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

/**
 * 实现在外部使用注册的函数
 */
public class SedonaTest {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("GIS", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.apache.sedona.core.serde.SedonaKryoRegistrator");
        SedonaSQLRegistrator.registerAll(spark);
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<Row> rdd = jsc.parallelize(Arrays.asList(
                RowFactory.create(116.321539, 40.008615),
                RowFactory.create(116.321618, 40.008467)
        ));
        Dataset<Row> dataset = spark.createDataFrame(rdd, StructType.fromDDL("longitude Double,latitude double"));
        dataset.show();
        dataset.printSchema();
        Function st_point = spark.catalog().getFunction("ST_Point");
        System.out.println(st_point.isTemporary());
        Dataset<Row> STdataset = dataset.withColumn("ST_Point",
                functions.callUDF("ST_Point", functions.col("longitude"), functions.col("latitude")));
        STdataset.show();
        STdataset.printSchema();
        //  error::  Exception in thread "main" java.lang.IllegalArgumentException: Can't get JDBC type for array<tinyint>
        //  不能写入数据到数据库
        pcon.PushToPSql(STdataset,"20220618test","overwrite");

    }
}
