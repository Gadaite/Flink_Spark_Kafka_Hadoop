package WorkAoutSpark.Main20220630;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;

public class Test {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("test", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark,
                "select * from trajectlonlatbyday where \"pointCount\" = 12689 limit 1");
        dataset.select("linestring").toJavaRDD().map(new Function<Row, Geometry>() {
            @Override
            public Geometry call(Row v1) throws Exception {
                Geometry linestring = new WKBReader().read(WKBReader.hexToBytes(v1.getString(v1.fieldIndex("linestring"))));
                return linestring;
            }
        }).take(1).forEach(x -> System.out.println(x));
    }
}
