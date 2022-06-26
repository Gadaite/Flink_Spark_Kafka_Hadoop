package WorkAoutSpark.Main20220626;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 数据量太大，尝试进行多次执行
 */
public class TaxiDataSummary implements Serializable {
    public static void main(String[] args) throws Exception {
        //  创建JavaBean
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220626\\TaxiiData.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220626";
            autoCreatePSqlBean.generateTables = new String[]{"taxidata"};
            autoCreatePSqlBean.generate();
        }else {
            System.out.println("file is Already exists!");
        }
        //  创建spark执行环境
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("TaxiDataClean", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        //  读取索引表的最大最小值
        Dataset<Row> indexDataSet = pcon.GetDataSetByProperties(spark, "select min(\"vehicleNum\") as min ,max(\"vehicleNum\") as max from taxidataindex");
        List<Row> collect = indexDataSet.toJavaRDD().collect();
        int min = collect.get(0).getInt(collect.get(0).fieldIndex("min"));//    22223
        int max = collect.get(0).getInt(collect.get(0).fieldIndex("max"));//    36950
        //  构造sql语句，每1000做一次计算
        List<String> sqls = new ArrayList<>();
        for (int i=(min/10)*10 ;i <= ((max / 10) + 1) * 10 ;i=i + 1000){
            String sql = "select * from taxidata where \"vehicleNum\" >= " + i + " and \"vehicleNum\" <= " + (i+1000);
            sqls.add(sql);
        }
        sqls.forEach(x ->{
            TaxiDataSummary taxiDataSummary = new TaxiDataSummary();
            try {
                taxiDataSummary.ExecFunction(x,spark,pcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //  更新表的数据类型
        PostgresqlConnect postgresqlConnect = new PostgresqlConnect();
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataofcar ALTER COLUMN \"endPoint\" TYPE geometry USING \"endPoint\"::geometry");
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataofcar ALTER COLUMN \"lineString\" TYPE geometry USING \"lineString\"::geometry");
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataofcar ALTER COLUMN \"startPoint\" TYPE geometry USING \"startPoint\"::geometry");
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataoforder ALTER COLUMN \"endPoint\" TYPE geometry USING \"endPoint\"::geometry");
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataoforder ALTER COLUMN \"startPoint\" TYPE geometry USING \"startPoint\"::geometry");
        postgresqlConnect.ExecPSql("ALTER TABLE public.taxidataoforder ALTER COLUMN \"lineString\" TYPE geometry USING \"lineString\"::geometry");

    }
    public void ExecFunction(String sql,SparkSession spark,PostgresqlJdbcCon pcon) throws Exception {
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, sql);
        JavaRDD<Taxidata> datasouceRDD = dataset.toJavaRDD()
                .map(new RowToJavaBean<Taxidata>(new GetDDL().GetGadaiteDDL(dataset), Taxidata.class));
        //  按照出租车编号进行分组
        JavaPairRDD<Integer, Iterable<Taxidata>> VehicleNumGroupRDD = datasouceRDD.mapToPair(new PairFunction<Taxidata, Integer, Taxidata>() {
            @Override
            public Tuple2<Integer, Taxidata> call(Taxidata taxidata) throws Exception {
                return new Tuple2<>(taxidata.getVehicleNum(),taxidata);
            }
        }).groupByKey();
        JavaRDD<Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>>> RESRDD = VehicleNumGroupRDD
                .map(new CorrelationStatisticalAnalysis("EPSG:4326"));
//        RESRDD.foreach(x -> System.out.println(x));
        JavaRDD<Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>>> cache = RESRDD.cache();
        //  出驻车统计数据入库
        JavaRDD<TaxiDataOfCar> carRes = cache.map(new Function<Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>>, TaxiDataOfCar>() {
            @Override
            public TaxiDataOfCar call(Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>> v1) throws Exception {
                return v1._1;
            }
        });
        Dataset<Row> dataFrame = spark.createDataFrame(carRes, TaxiDataOfCar.class);
        pcon.PushToPSql(dataFrame,"taxiDataOfCar","append");
        JavaRDD<TaxiDataOfOrder> orderRes = cache.map(new Function<Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>>, Iterable<TaxiDataOfOrder>>() {
            @Override
            public Iterable<TaxiDataOfOrder> call(Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>> v1) throws Exception {
                return v1._2;
            }
        }).flatMap(new FlatMapFunction<Iterable<TaxiDataOfOrder>, TaxiDataOfOrder>() {
            @Override
            public Iterator<TaxiDataOfOrder> call(Iterable<TaxiDataOfOrder> taxiDataOfOrders) throws Exception {
                return taxiDataOfOrders.iterator();
            }
        });
        Dataset<Row> dataFrame1 = spark.createDataFrame(orderRes, TaxiDataOfOrder.class);
        pcon.PushToPSql(dataFrame1,"taxiDataOfOrder","append");
        System.out.println("已完成一次数据读写");
    }
}
