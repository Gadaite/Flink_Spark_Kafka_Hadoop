package WorkAoutSpark.Main20220619;

import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

public class JTSTest {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220619\\BrightkiteTotalcheckins.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220619";
            autoCreateMysqlBean.generateTables = new String[]{"Brightkite_totalCheckins"};
            autoCreateMysqlBean.generate();
        }
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("JTS", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from Brightkite_totalCheckins limit 1000");
        dataset.printSchema();
        dataset.filter(new FilterFunction<Row>() {
            @Override
            public boolean call(Row value) throws Exception {
                return false;
            }
        }).show();



    }
}
