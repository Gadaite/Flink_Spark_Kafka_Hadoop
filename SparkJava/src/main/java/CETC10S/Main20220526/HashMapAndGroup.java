package CETC10S.Main20220526;

import CETC10S.ToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import java.util.*;

public class HashMapAndGroup {

    public static void main(String[] args) throws Exception {
        MysqlJdbcCon mysqlJdbcCon = new MysqlJdbcCon();
        mysqlJdbcCon.init();
        SparkSession spark = mysqlJdbcCon.getSparkSesssion("HashMapAndGroup", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = mysqlJdbcCon.GetDataSetByProperties(spark, "( select * from audi ) t");
        JavaRDD<Row> audi = dataset.toJavaRDD();
        JavaPairRDD<String, Iterable<Row>> pairRDD = audi.groupBy(new GroupFunction());
        JavaRDD<HashMap<String, List<Row>>> mapRDD = pairRDD.map(new MMapFunction());
        List<HashMap<String, List<Row>>> collect = mapRDD.collect();
        collect.forEach(x ->{
            List<Row> rows = new ArrayList<>();
            x.values().forEach(y -> {
                rows.addAll(y);
            });
            x.keySet().forEach(y -> System.out.println(y));
            Dataset<Row> resdf = spark.createDataFrame(jsc.parallelize(rows), StructType.fromDDL(mysqlJdbcCon.GetDDL(dataset)));
            resdf.show();
            try {
                Properties prop = new Properties();
                prop.put("user", mysqlJdbcCon.init().getProperty("username"));
                prop.put("password", mysqlJdbcCon.init().getProperty("pwd"));
                String tablename = resdf.head().getString(resdf.head().fieldIndex("model"))
                        + "_" +
                        String.valueOf(resdf.head().getInt(resdf.head().fieldIndex("year")));
                /**
                 * spark会自动创建表
                 */
//                resdf.write().mode(SaveMode.Append).jdbc(mysqlJDBC.init().getProperty("url"),tablename,prop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
