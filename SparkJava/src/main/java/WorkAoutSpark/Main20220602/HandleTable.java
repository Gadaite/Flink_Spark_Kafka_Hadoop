package WorkAoutSpark.Main20220602;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import shapeless.ops.nat;
import shapeless.ops.zipper;

import java.io.File;
import java.util.Iterator;

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
        /**
         * 对RDD进行聚合统计price的总和
         */
        JavaPairRDD<String, Audi> pairRDD = AudiRDD.mapToPair(new KeyValueFunction());
        JavaPairRDD<String, Audi> Sum_pairRDD = pairRDD.reduceByKey(new SumFunction());
        Sum_pairRDD.foreach(x-> System.out.println(x));
        /**
         * 对RDD进行分组并计算组内的个数
         */
    }
}
