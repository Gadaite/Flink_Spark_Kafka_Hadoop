package WorkAoutSpark.Main20220530;

import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.List;

public class HandleTable extends MysqlJdbcCon {
    public static void main(String[] args) throws Exception {
        /**
         * 生成JavaBean类1
         */
        AutoCreateMysqlBean createMysqlBean = new AutoCreateMysqlBean();
        AutoCreateMysqlBean.basePath = "F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\";
        AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220530";
        createMysqlBean.generateTables = new String[]{"TaxiRoutesBoG"};
        createMysqlBean.generate();
        /**
         * 创建Spark环境，读取数据源
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("HandleTable", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from TaxiRoutesBoG");
        dataset.show(10);
        /**
         * 数据进行分组
         */
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaPairRDD<String, Iterable<Row>> groupedRDD = javaRDD.groupBy(new GroupFunction());
        JavaRDD<List<Row>> map = groupedRDD.map(new SummaryFunction());
    }
}
