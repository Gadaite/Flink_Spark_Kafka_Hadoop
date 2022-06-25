package WorkAoutSpark.Main20220625;


import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;

public class TrajectoryAndArea {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("TrajectoryAndArea", "ERROR");
        //  选取点数少于1000个点的轨迹
        Dataset<Row> dataset1 = pcon.GetDataSetByProperties(spark,
                "select * from trajectlonlatbyday where trajectlonlatbyday.\"pointCount\"  <= 1000");
        //  读取全国各个省市区域数据集
        Dataset<Row> dataset2 = pcon.GetDataSetByProperties(spark, "select * from chinacityboundary");
        JavaRDD<TrajectoryLonLatbyday> dataRDD = dataset1.toJavaRDD().map(new TransDataToGeoClass());
        JavaRDD<ChinaCityBoundary> cityRDD = dataset2.toJavaRDD().map(new TransCityToGeoClass());
        List<ChinaCityBoundary> cityInfo = cityRDD.collect();


    }
}
