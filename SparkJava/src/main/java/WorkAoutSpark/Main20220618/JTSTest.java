package WorkAoutSpark.Main20220618;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.sedona.core.spatialRDD.PointRDD;
import org.apache.sedona.core.spatialRDD.PolygonRDD;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.sedona.viz.sql.utils.SedonaVizRegistrator;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.sedona_sql.expressions.ST_GeomFromWKB;
import org.apache.spark.sql.sedona_sql.expressions.ST_Point;
import org.apache.spark.sql.types.StructType;
import org.geolatte.geom.json.GeometrySerializer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKBReader;
import scala.Function1;
import scala.Tuple3;

import java.io.File;
import java.util.Arrays;

public class JTSTest {
    public static void main(String[] args) throws Exception {
        /**
         * 从数据库创建实体类JavaBean
         */
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220618\\Chinacityboundary.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220618";
            autoCreatePSqlBean.generateTables = new String[]{"chinacityboundary"};
            autoCreatePSqlBean.generate();
        }else {
            System.out.println("Chinacityboundary.java is already exists !");
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("JTS", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.apache.sedona.core.serde.SedonaKryoRegistrator");
        spark.conf().set("spark.kryo.registrator", "org.datasyslab.geospark.serde.GeoSparkKryoRegistrator");
        SedonaSQLRegistrator.registerAll(spark);
        SedonaVizRegistrator.registerAll(spark);
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> datasource = pcon.GetDataSetByProperties(spark, "select * from chinacityboundary");
        datasource.createOrReplaceTempView("datasource");
        Dataset<Row> dataset = spark.sql("select acroutes,adcode,st_geomfromwkb(center) as center, " +
                "st_geomfromwkb(centroid) as centroid , childrenNum, st_geomfromwkb(region) as region ," +
                "level, name, parentCenter, subFeatureIndex from datasource limit 5");
        dataset.show();
//        pcon.PushToPSql(dataset,"20220618test","overwrite");

    }
}
