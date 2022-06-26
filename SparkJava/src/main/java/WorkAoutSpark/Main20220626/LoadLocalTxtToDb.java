package WorkAoutSpark.Main20220626;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 此项目首先使用到的数据是深圳市2013年11月22日的出租车GPS定位数据，共计4千多万条数据，占用内存2.1GB。
 * 数据来源于: https://people.cs.rutgers.edu/~dz220/data.html 由罗格斯大学的Desheng Zhang教授分享于个人主页，
 * 包含了车号、时间、经度、纬度、空车（1为载客，0为空车）和速度五个特征。
 */
public class LoadLocalTxtToDb {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("readtxt", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<String> rdd = jsc.textFile("file:\\D:\\googledownload\\TaxiData\\TaxiData.txt");
        rdd.take(1).forEach(x -> System.out.println(x));
        JavaRDD<Taxiidata> taxiiDataJavaRDD = rdd.map(new Function<String, Taxiidata>() {
            @Override
            public Taxiidata call(String v1) throws Exception {
                String[] str = v1.split(",");
                int VehicleNum = Integer.parseInt(str[0]);
                String Stime = "2013-11-22 " + String.valueOf(str[1]) + ".000";
                Double longitude = Double.valueOf(str[2]);
                Double latitude = Double.valueOf(str[3]);
                Double OpenStatus = Double.valueOf(str[4]);
                Double Speed = Double.valueOf(str[5]);
                return new Taxiidata(VehicleNum, Stime, longitude, latitude, OpenStatus, Speed);
            }
        });
        Dataset<Row> dataFrame = spark.createDataFrame(taxiiDataJavaRDD, Taxiidata.class);
        System.out.println(dataFrame.count());
        pcon.PushToPSql(dataFrame,"TaxiData","Overwrite");
    }
}
