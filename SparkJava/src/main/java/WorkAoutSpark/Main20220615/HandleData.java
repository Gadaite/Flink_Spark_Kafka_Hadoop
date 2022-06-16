package WorkAoutSpark.Main20220615;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.*;
import scala.Tuple2;

import java.io.File;

/**
 * 这里只读取10天的数据量就够了
 */
public class HandleData {
    public static void main(String[] args) throws Exception {
        /**
         * 创建JavaBean类
         */
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220615\\Trajectlonlat.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220615";
            autoCreatePSqlBean.generateTables = new String[]{"trajectlonlat"};
            autoCreatePSqlBean.generate();
        }else {
            System.out.println("JavaBean is already exists!");
        }
        /**
         * spark执行环境，并读取数据
         */
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("HandleDate", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from trajectlonlat " +
                "where datetime >= '2009-01-01 00:00:00' and datetime <= '2009-01-10 00:00:00'");
        dataset.show();
        dataset.printSchema();
        System.out.println(dataset.count());
        /**
         * 按照每天的数据进行分组
         */
        JavaRDD<Trajectlonlat> trajectlonlatJavaRDD = dataset.toJavaRDD().
                map(new RowToJavaBean<Trajectlonlat>(new GetDDL().GetGadaiteDDL(dataset), Trajectlonlat.class));
        JavaPairRDD<String, Iterable<Trajectlonlat>> dayPairRDD = trajectlonlatJavaRDD.groupBy(new GroupFunction());
        /**
         * 对数据进行处理
         */
        JavaRDD<TrajectoryByDayModel> ResRdd = dayPairRDD.map(new AnalysisFunction());
        ResRdd.foreach(x -> System.out.println(x));
        /**
         * 创建DataSet
         */
        RDD<TrajectoryByDayModel> rdd = ResRdd.rdd();
        Dataset<TrajectoryByDayModel> resdataset = spark.createDataset(ResRdd.rdd(), Encoders.bean(TrajectoryByDayModel.class));
        resdataset.show(20);
    }
}
