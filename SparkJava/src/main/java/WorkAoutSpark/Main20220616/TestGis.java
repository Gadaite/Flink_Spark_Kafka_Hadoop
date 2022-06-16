package WorkAoutSpark.Main20220616;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.sedona.core.serde.SedonaKryoRegistrator;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.catalog.Function;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

/**
 * 目的使用spark直接使用ST_相关的GIS函数
 * 任务艰巨，难以实现
 * 有时间再继续
 */
public class TestGis {
    public static void main(String[] args) throws AnalysisException {
//        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
//        SparkSession spark = pcon.getSparkSesssion("Gis", "ERROR");
//        SedonaSQLRegistrator.registerAll(spark);
//        SedonaKryoRegistrator sedonaKryoRegistrator = new SedonaKryoRegistrator();
//        sedonaKryoRegistrator.registerClasses(spark);
//        Function ST_Point = spark.catalog().getFunction("ST_Point");
//        System.out.println(ST_Point.getClass().getName());
//        ST_Point.name()new Coordinate(1.2,1.6).


    }
}
