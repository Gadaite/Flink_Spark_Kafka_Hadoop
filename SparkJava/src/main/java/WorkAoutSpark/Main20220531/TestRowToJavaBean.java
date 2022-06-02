package WorkAoutSpark.Main20220531;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;
import java.util.List;

public class TestRowToJavaBean extends MysqlJdbcCon {
    public static void main(String[] args) throws Exception {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("RowToJavaBean", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from TaxiRoutesBoG limit 10");
        String ddl = new GetDDL().GetGadaiteDDL(dataset);
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<Taxiroutesbog> JavaBeanRDD = javaRDD.map(new RowToJavaBean<Taxiroutesbog>(ddl, Taxiroutesbog.class));
        List<Taxiroutesbog> collect = JavaBeanRDD.collect();
        System.out.println(collect);


    }
}
