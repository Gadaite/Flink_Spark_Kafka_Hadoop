package WorkAoutSpark.Main20220611;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

public class PlotTrajectoryAnalysis {
    public static void main(String[] args) throws Exception {
        /**
         * 创建javaBean类型
         */
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220611\\BrightkiteTrajectory2008.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220611";
            autoCreateMysqlBean.generateTables = new String[]{"BrightkiteTrajectory2008"};
            autoCreateMysqlBean.generate();
        }else {
            System.out.println("Class is already exists!");
        }
        /**
         * 读取数据源
         * 两次转换Javabean
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("hdzw", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from BrightkiteTrajectory2008 ");
        dataset.show(10);
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<Brightkitetrajectory2008> javaRDD1 = javaRDD.map(
                new RowToJavaBean<Brightkitetrajectory2008>(new GetDDL().GetGadaiteDDL(dataset), Brightkitetrajectory2008.class));
        JavaRDD<Format2008> javaRDD2 = javaRDD1.map(new TransFormat());
        javaRDD2.take(3).forEach(x -> System.out.println(x));
        /**
         *  作图分析
         */

    }
}
