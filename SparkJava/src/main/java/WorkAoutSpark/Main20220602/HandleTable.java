package WorkAoutSpark.Main20220602;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

public class HandleTable {
    public static void main(String[] args) throws Exception {
        /**
         * 生成JavaBean文件
         */
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220602\\Audi.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220602";
            autoCreateMysqlBean.generateTables = new String[]{"audi"};
            autoCreateMysqlBean.generate();
        }else{
            System.out.println("File already exists!");
        }
        /**
         * 创建Spark执行环境并读取数据
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("handletable", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from audi");
        /**
         * dataset转JavaRDD，转RDD<JavaBean>
         */
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        String ddl = new GetDDL().GetGadaiteDDL(dataset);
        JavaRDD<Audi> AudiRDD = javaRDD.map(new RowToJavaBean<Audi>(ddl, Audi.class));
        AudiRDD.foreach(x -> System.out.println(x));
    }
}
