package WorkAoutSpark.Main20220529;

/**
 * made by Gadaite
 * 测试自定义工具类的使用情况
 */

import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import GadaiteToolGeo.GeoCoordinate;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HandleTable extends MysqlConnect implements Serializable {
    public static void main(String[] args) throws Exception{
        /**
         * 调用封装类，自动创建JavaBean文件到当前目录
         */
        String FilePath = "F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\";
        String PackageName = "WorkAoutSpark.Main20220529";
        AutoCreateMysqlBean createMysqlBean = new AutoCreateMysqlBean();
        AutoCreateMysqlBean.basePath = FilePath;
        AutoCreateMysqlBean.packageOutPath = PackageName;
        createMysqlBean.generateTables = new String[]{"BIXIMontrealOD2014","BIXIMontrealStations2014"};
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
         * dataset转RDD
         */
        JavaRDD<Row> OD2014RDD = datasetOD2014.toJavaRDD();
        JavaRDD<Row> Station2014RDD = datasetStations2014.toJavaRDD();
        /**
         * 将Station2014RDD表转换为HashMap结构
         * 其中的经纬度使用自定义的数据类型
         */
        JavaRDD<HashMap<Integer, GeoCoordinate>> map = Station2014RDD.map(new StationHashMap());
        List<HashMap<Integer, GeoCoordinate>> collect = map.collect();
        for (HashMap<Integer, GeoCoordinate> tmp : collect){
            for (Map.Entry<Integer, GeoCoordinate> tmptmp : tmp.entrySet()){
                System.out.print(tmptmp.getKey());
                System.out.print(": " + tmptmp.getValue().getLon() + " , " + tmptmp.getValue().getLat());
            }
            System.out.println();
        }

        /**
         * 将BIXIMontrealOD2014表中的start_station_code以及end_station_code转换经纬度数据
         */
        OD2014RDD.map(new TransToLonLat());
    }
}
