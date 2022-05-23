package JavaBean;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class MkSparkSesssion {
    public static void main(String[] args) {

    }
    public static void getSparkSession(String name,String loglevel,Boolean bool){
        if (bool == false){
            SparkSession spark = SparkSession.builder().
                    config("spark.ui.showConsoleProgress", "false").master("local[*]").appName(name).getOrCreate();
            JavaSparkContext.fromSparkContext(spark.sparkContext()).setLogLevel(loglevel);
        }else {
            SparkSession spark = SparkSession.builder().
                    config("spark.ui.showConsoleProgress", "false").master("local[*]").appName(name).enableHiveSupport().getOrCreate();
            JavaSparkContext.fromSparkContext(spark.sparkContext()).setLogLevel(loglevel);
        }
    }
}
