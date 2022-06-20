package WorkAoutSpark.Main20220620;

import GadaiteJavaBeanFromDBS.Audi;
import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;

public class DatasetOperate {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon mysqlJdbcCon = new MysqlJdbcCon();
        SparkSession spark = mysqlJdbcCon.getSparkSesssion("ds", "ERROR");
        Dataset<Row> dataset = mysqlJdbcCon.GetDataSetByProperties(spark, "select * from audi");
        dataset.show();
        JavaRDD<Audi> map = dataset.toJavaRDD().map(new RowToJavaBean<Audi>(new GetDDL().GetGadaiteDDL(dataset), Audi.class));
        map.foreach(x -> System.out.println(x));


    }
}
