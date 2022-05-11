package CETC10S.Main20220511;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
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
        JavaRDD<AudiModel> audiModelJavaRDD = javaRDD.map(new Function<Row, AudiModel>() {
            @Override
            public AudiModel call(Row v1) throws Exception {
                AudiModel audiModel = new AudiModel();
                audiModel.setModel(v1.getString(v1.fieldIndex("model")));
                audiModel.setYear(v1.getInt(v1.fieldIndex("year")));
                audiModel.setPrice(v1.getInt(v1.fieldIndex("price")));
                audiModel.setTransmission(v1.getString(v1.fieldIndex("transmission")));
                audiModel.setMileage(v1.getInt(v1.fieldIndex("mileage")));
                audiModel.setFuelType(v1.getString(v1.fieldIndex("fuelType")));
                audiModel.setTax(v1.getInt(v1.fieldIndex("tax")));
                audiModel.setMpg(v1.getDouble(v1.fieldIndex("mpg")));
                audiModel.setEngineSize(v1.getDouble(v1.fieldIndex("engineSize")));
                return audiModel;
            }
        });
        JavaRDD<ResModel> resModelJavaRDD = audiModelJavaRDD.map(new Function<AudiModel, ResModel>() {
            @Override
            public ResModel call(AudiModel v1) throws Exception {
                ResModel resModel = new ResModel();
                resModel.setModel(v1.getModel());
                resModel.setYear(v1.getYear());
                resModel.setPrice(v1.getPrice());
                resModel.setTransmission(v1.getTransmission());
                resModel.setMileage(v1.getMileage());
                resModel.setFuelType(v1.getFuelType());
                resModel.setTax(v1.getTax());
                resModel.setMpg(v1.getMpg());
                resModel.setEngineSize(v1.getEngineSize());
                int price = v1.getPrice();
                int level = price / 1000;
                resModel.setLevel(level);
                return resModel;
            }
        });
        JavaPairRDD<String, Iterable<ResModel>> javaPairRDD = resModelJavaRDD.groupBy(new Function<ResModel, String>() {
            @Override
            public String call(ResModel v1) throws Exception {
                String model = v1.getModel();
                String year = String.valueOf(v1.getYear());
                return model + "---" + year;
            }
        });
        spark.createDataFrame(resModelJavaRDD,ResModel.class).show();
        JavaPairRDD<String, Iterable<ResModel>> iterableJavaPairRDD = javaPairRDD.reduceByKey(new Function2<Iterable<ResModel>, Iterable<ResModel>, Iterable<ResModel>>() {
            @Override
            public Iterable<ResModel> call(Iterable<ResModel> v1, Iterable<ResModel> v2) throws Exception {
                /**
                 * 测试分组之后，进行聚合时发现：
                 *      相同分组的情况下，其他的字段不同的时候，怎么处理选择哪一个？
                 *      按照SQL的写法也是相同的
                 */
                return null;
            }
        });
    }
}
