package WorkAoutSpark.Main20220603;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.File;

/**
 * made by Gadaite
 * 测试对实体类对象的数据进行按照某个字段进行排序
 * 方式一：通过键值对RDD，按照键进行排序
 */
public class SortClassObjectByPairRDD {
    public static void main(String[] args) throws Exception {
        /**
         * 创建实体类
         */
        String basepath = "F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220603\\Audi.java";
        String packagename = "WorkAoutSpark.Main20220603";
        File file = new File(basepath);
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = packagename;
            autoCreateMysqlBean.generateTables = new String[]{"audi"};
            autoCreateMysqlBean.generate();
        }else {
            System.out.println("File already exists!");
        }
        /**
         * 读取数据源
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("SortClassObject", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from audi limit 10");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        /**
         * 获取指定格式的DDL
         */
        String ddl = new GetDDL().GetGadaiteDDL(dataset);
        /**
         * 封装为特定类型
         */
        JavaRDD<Audi> AudiRDD = javaRDD.map(new RowToJavaBean<Audi>(ddl, Audi.class));
        JavaPairRDD<Integer, Audi> pairRDD = AudiRDD.mapToPair(new PairFunction<Audi, Integer, Audi>() {
            @Override
            public Tuple2<Integer, Audi> call(Audi audi) throws Exception {
                Integer year = audi.getYear();
                return new Tuple2<>(year, audi);
            }
        });
//        pairRDD.foreach(x-> System.out.println(x));
        /**
         * 按照键进行排序
         */
        JavaPairRDD<Integer, Audi> integerAudiJavaPairRDD = pairRDD.sortByKey();
        /**
         * map只取到值
         */
        JavaRDD<Audi> sortRDD = integerAudiJavaPairRDD.map(new Function<Tuple2<Integer, Audi>, Audi>() {
            @Override
            public Audi call(Tuple2<Integer, Audi> v1) throws Exception {
                Audi audi = v1._2;
                return audi;
            }
        });
        /**
         * 输出，触发行动算子
         */
        sortRDD.foreach(x-> System.out.println(x));

    }
}
