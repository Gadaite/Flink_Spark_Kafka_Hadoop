package WorkAoutSpark.Main20220608;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

public class HandleTable {
    public static void main(String[] args) throws Exception {
        //  创建对应的JavaBean
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220608\\BrightkiteTotalcheckins.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220608";
            autoCreateMysqlBean.generateTables = new String[]{"Brightkite_totalCheckins"};
            autoCreateMysqlBean.generate();
        }else {
            System.out.println("Class is already exists！");
        }
        //  读取数据
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("Handletable", "ERRor");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark,
                "select * from Brightkite_totalCheckins where YEAR(`time`) = 2008");
        dataset.printSchema();
        System.out.println(dataset.count());
        //  转换数据类型为JavaBean
        JavaRDD<BrightkiteTotalcheckins> maprdd = dataset.toJavaRDD().map(new RowToJavaBean<BrightkiteTotalcheckins>(new GetDDL().GetGadaiteDDL(dataset),
                BrightkiteTotalcheckins.class));
        //  转换互获取其Geohash的值
        JavaRDD<BrightkiteTotalcheckins> hash = maprdd.map(new GetGeoHash(9));
        //  按照用户ID分组
        JavaPairRDD<Integer, Iterable<BrightkiteTotalcheckins>> pairRDD = hash.groupBy(new GroupFunction());
        pairRDD.take(7).forEach(x -> System.out.println(x));

    }
}
