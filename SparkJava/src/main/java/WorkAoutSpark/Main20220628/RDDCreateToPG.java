package WorkAoutSpark.Main20220628;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresSqlEntityInsert;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.Serializable;

public class RDDCreateToPG implements Serializable {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220628\\Trajectlonlathash9.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220628";
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
        trajectlonlathash9JavaRDD.foreach(new PostgresSqlEntityInsert(Trajectlonlathash9.class,
                new PostgresqlConnect(),"test20220627"));
        new PostgresSqlEntityInsert().ExecInsertEntity(trajectlonlathash9JavaRDD,"test20220627");
    }
}
