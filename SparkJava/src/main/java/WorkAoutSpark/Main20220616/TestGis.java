package WorkAoutSpark.Main20220616;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 待完成目标：
 * 将函数的值注册到外部直接使用UDF函数
 */
public class TestGis {
    public static void main(String[] args) throws AnalysisException {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("Gis", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.apache.sedona.core.serde.SedonaKryoRegistrator");
        SedonaSQLRegistrator.registerAll(spark);
        Dataset<Row> dataset = spark.sql("select ST_point(1.2,1.3)");
        dataset.show();
        dataset.printSchema();

    }
}
