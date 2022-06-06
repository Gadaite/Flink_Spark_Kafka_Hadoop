package WorkAoutSpark.Main20220605;

import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


/**
 * made by Gadaite
 * 用于本地数据转换并将数据上传到Mysql
 * 并更改表的结构
 * 一次性使用代码不建议反复执行
 */
public class TransCsvOneToDbeaver {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("TransData", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.read().format("csv").option("sep", "\t")
                .load("file:\\D:\\googledownload\\loc-brightkite_edges.txt\\Brightkite_edges.csv");
        Dataset<Row> dataset1 = dataset.withColumnRenamed("_c0", "userA").withColumnRenamed("_c1", "userB");
        dataset1.show(10);
        dataset1.printSchema();
        con.PushToMySql(dataset1,"Brightkite_edges","overwrite");
        MysqlConnect connect = new MysqlConnect();
        /**
         * 更新字段类型
         */
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_edges MODIFY COLUMN userA int NULL;");
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_edges MODIFY COLUMN userB int NULL;");
    }
}
