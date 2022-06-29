package WorkAoutSpark.Main20220629;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

public class TestCreateToPSql {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220629\\Trajectlonlathash9.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220629";
            autoCreatePSqlBean.generateTables = new String[]{"trajectlonlathash9"};
            autoCreatePSqlBean.generate();
        }else {
            System.out.println("file is Already exists!");
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("RowAddSchema", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from trajectlonlathash9 limit 10");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<Trajectlonlathash9> trajectlonlathash9JavaRDD = javaRDD
                .map(new RowToJavaBean<Trajectlonlathash9>(new GetDDL().GetGadaiteDDL(dataset), Trajectlonlathash9.class));
        //  M1:创建表实体类表
//        new PostgresSqlEntityCreate().ExecCreateEntity(trajectlonlathash9JavaRDD,"test20220629");
        //  M2:创建表并写入数据实体类表
//        new PostgresSqlEntityCreate().ExecMakeEntity(trajectlonlathash9JavaRDD,"test20220629");
        //  M3:创建表并写入数据实体类表
//        trajectlonlathash9JavaRDD.foreach(new PostgresSqlEntityCreate<Trajectlonlathash9>(Trajectlonlathash9.class,
//                new PostgresqlConnect(),"test20220629"));
        //  M4:创建表Row表
//        new PostgresSqlRowCreate().ExecCreateRow(javaRDD,"test20220629");
        //  M5:创建表并写入数据Row表
//        new PostgresSqlRowCreate().ExecMakeRow(javaRDD,"test20220629");
        //  M6:创建表并写入数据Row表
        PostgresqlConnect postgresqlConnect = new PostgresqlConnect();
        javaRDD.foreach(new PostgresSqlRowCreate(javaRDD,postgresqlConnect,"test20220629"));
    }
}
