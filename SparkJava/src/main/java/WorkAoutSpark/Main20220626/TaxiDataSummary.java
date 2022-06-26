package WorkAoutSpark.Main20220626;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.File;

public class TaxiDataSummary {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220626\\TaxiiData.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220626";
            autoCreatePSqlBean.generateTables = new String[]{"taxidata"};
            autoCreatePSqlBean.generate();
        }else {
            System.out.println("file is Already exists!");
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("TaxiDataClean", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from taxidata");
        JavaRDD<Taxidata> datasouceRDD = dataset.toJavaRDD()
                .map(new RowToJavaBean<Taxidata>(new GetDDL().GetGadaiteDDL(dataset), Taxidata.class));
        //  按照出租车编号进行分组
        JavaPairRDD<Integer, Iterable<Taxidata>> VehicleNumGroupRDD = datasouceRDD.mapToPair(new PairFunction<Taxidata, Integer, Taxidata>() {
            @Override
            public Tuple2<Integer, Taxidata> call(Taxidata taxidata) throws Exception {
                return new Tuple2<>(taxidata.getVehicleNum(),taxidata);
            }
        }).groupByKey();
        JavaRDD<Tuple2<Iterable<TaxiDataOfCar>, Iterable<TaxiDataOfOrder>>> RESRDD = VehicleNumGroupRDD
                .map(new CorrelationStatisticalAnalysis());



    }
}
