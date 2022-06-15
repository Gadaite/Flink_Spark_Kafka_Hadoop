package WorkAoutSpark.Main20220615;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

public class VerificatTable {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("verification", "ERROR");
        Dataset<Row> trajectorydata = pcon.GetDataSetByProperties(spark, "select * from trajectlonlat");
        trajectorydata.toJavaRDD().groupBy(new Function<Row, String>() {
            @Override
            public String call(Row v1) throws Exception {
                String s = v1.get(v1.fieldIndex("datetime")).toString();
                return s;
            }
        }).map(new Function<Tuple2<String, Iterable<Row>>, Integer>() {
            int count;

            @Override
            public Integer call(Tuple2<String, Iterable<Row>> v1) throws Exception {

                v1._2.forEach(x -> {
                    count = count + 1;
                });
                return count;
            }
        }).foreach(x -> System.out.println(x));
    }
}
