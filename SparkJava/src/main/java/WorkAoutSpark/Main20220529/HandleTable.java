package WorkAoutSpark.Main20220529;

/**
 * made by Gadaite
 * 测试自定义工具类的使用情况
 */

import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.util.ArrayList;

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
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220529\\Audi.java");
        if (!file.getParentFile().exists()) {
            createMysqlBean.generateTables = new String[]{"audi"};
            createMysqlBean.generate();
        }else {
            System.out.println("Audi.Java is alreadly exists !");
        }
        /**
         * 调用封装类，使用创建Spark执行环境，并获取数据源
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("handleTable", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from audi limit 5");
        dataset.show();
        dataset.printSchema();
        /**
         * 测试生成的JavaBean文件的可用性
         */
        JavaRDD<Row> audiRDD = dataset.toJavaRDD();
        JavaRDD<Audi> audimodelRDD = audiRDD.map(new MMapFunction());
        audimodelRDD.foreach(x -> System.out.println(x));

    }
}
