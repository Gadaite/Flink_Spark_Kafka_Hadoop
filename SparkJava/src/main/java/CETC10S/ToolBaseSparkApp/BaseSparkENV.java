package CETC10S.ToolBaseSparkApp;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public  abstract class BaseSparkENV {
    public SparkSession getSparkSesssion(String appName,String loglevel){
        System.setProperty("hadoop.home.dir","F:\\HadoopWin");
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .appName(appName).master("local[*]").enableHiveSupport().getOrCreate();
        JavaSparkContext.fromSparkContext(spark.sparkContext()).setLogLevel(loglevel);
        return spark;
    }
    public abstract String ExecuteFunction() throws Exception;
}
