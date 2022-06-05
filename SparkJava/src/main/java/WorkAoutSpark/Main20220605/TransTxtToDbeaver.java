package WorkAoutSpark.Main20220605;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TransTxtToDbeaver {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("TransData", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.read().format("csv").option("sep", "\t")
                .load("file:\\D:\\googledownload\\loc-brightkite_edges.txt\\Brightkite_edges.csv");
        Dataset<Row> dataset1 = dataset.withColumnRenamed("_c0", "userA").withColumnRenamed("_c1", "userB");
//        dataset1.show(10);
//        con.PushToMySql(dataset1,"Brightkite_edges","append");
        Dataset<Row> dataset2 = spark.read().format("csv").option("sep", "\t")
                .load("file:\\D:\\googledownload\\loc-brightkite_totalCheckins.txt\\Brightkite_totalCheckins.csv");
//        dataset2.withColumns()

    }
}
