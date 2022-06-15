package WorkAoutSpark.Main20220614;

import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import scala.Tuple4;
import shapeless.Tuple;

import java.sql.Timestamp;

public class ETLFormatPltFile {
    public static void main(String[] args) {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("plt", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<String> pltRDD = jsc.textFile(
                "file:\\D:\\googledownload\\Geolife Trajectories 1.3\\Data\\000\\Trajectory\\20081023025304.plt");
        JavaRDD<String> filterRDD = pltRDD.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String v1) throws Exception {
                return v1.length() > "0,2,255,My Track,0,0,2,8421376".length();
            }
        });
        JavaRDD<ETLdataJavaBean> mapRDD = filterRDD.map(new Function<String, ETLdataJavaBean>() {
            @Override
            public ETLdataJavaBean call(String v1) throws Exception {
                /**
                 * 部分字符不选择[2]全为空
                 * [3]为一年的第几天
                 * [5],[6]合并为时间戳
                 */
                String[] line = v1.split(",");
                Double lat = Double.valueOf(line[0]);
                Double lon = Double.valueOf(line[1]);
                Double alt = Double.valueOf(line[4]);
                Timestamp f4 = Timestamp.valueOf(line[5] + " " + line[6]);
                return new ETLdataJavaBean(lat, lon, alt, f4);
            }
        });
        mapRDD.take(10).forEach(x -> System.out.println(x));
    }
}
