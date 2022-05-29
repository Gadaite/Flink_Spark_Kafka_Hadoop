package WorkAoutSpark.Main20220529;

/**
 * made by Gadaite
 * 测试自定义工具类的使用情况
 */

import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class HandleTable extends MysqlConnect {
    public static void main(String[] args) throws Exception{
        /**
         * 调用封装类，自动创建JavaBean文件到当前目录
         */
        String FilePath = "F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\";
        String PackageName = "WorkAoutSpark.Main20220529";
        AutoCreateMysqlBean createMysqlBean = new AutoCreateMysqlBean();
        AutoCreateMysqlBean.basePath = FilePath;
        AutoCreateMysqlBean.packageOutPath = PackageName;
        createMysqlBean.generateTables = new String[]{"BIXIMontrealOD2014"};
        createMysqlBean.generate();

        /**
         * 调用封装类，使用创建Spark执行环境，并获取数据源
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("handleTable", "ERROR");
        Dataset<Row> datasetOD2014 = con.GetDataSetByProperties(spark, "select * from BIXIMontrealOD2014");
        datasetOD2014.show();
        datasetOD2014.printSchema();

        Dataset<Row> datasetStations2014 = con.GetDataSetByProperties(spark, "select * from BIXIMontrealStations2014");
        datasetStations2014.show();
        datasetStations2014.printSchema();
        /**
         * 测试生成的JavaBean文件的可用性
         */
        JavaRDD<Row> OD2014RDD = datasetOD2014.toJavaRDD();
//        JavaRDD<Audi> audimodelRDD = BIXIMontrealOD2014RDD.map(new MMapFunction());
//        audimodelRDD.foreach(x -> System.out.println(x));
        /**
         *
         */

    }
}
