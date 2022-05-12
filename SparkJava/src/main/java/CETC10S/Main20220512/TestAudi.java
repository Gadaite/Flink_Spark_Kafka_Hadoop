package CETC10S.Main20220512;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestAudi {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","F:\\HadoopWin");
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .master("local[*]").appName("20220511").enableHiveSupport().getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.read().format("jdbc").option("driver","com.mysql.cj.jdbc.Driver")
                .option("url","jdbc:mysql://192.168.1.10/Gadaite")
                .option("user","root")
                .option("password","LYP809834049")
                .option("dbtable","audi")
                .load();
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        /**
         * step1
         * 过滤阶段，筛选model列不为空的情况
         */
        JavaRDD<Row> model = javaRDD.filter(new Function<Row, Boolean>() {
            @Override
            public Boolean call(Row v1) throws Exception {
                if (v1.getString(v1.fieldIndex("model")) != null) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        /**
         * step2
         * 选择两个字段进行分组
         */
        JavaPairRDD<String, Iterable<Row>> javaPairRDD = model.groupBy(new Function<Row, String>() {
            @Override
            public String call(Row v1) throws Exception {
                String model1 = v1.getString(v1.fieldIndex("model"));
                String year = String.valueOf(v1.getInt(v1.fieldIndex("year")));
                return model1 + "---" + year;
            }
        });
        javaPairRDD.foreach(x -> System.out.println(x));
        /**
         * step3
         * 只对transmission字段进行聚合
         */
        javaPairRDD.flatMap(new Handle());
    }
}
