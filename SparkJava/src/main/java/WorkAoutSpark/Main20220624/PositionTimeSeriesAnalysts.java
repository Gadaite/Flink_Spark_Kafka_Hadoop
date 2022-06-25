package WorkAoutSpark.Main20220624;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PositionTimeSeriesAnalysts {
    public static void main(String[] args) throws Exception {
        /**
         * 自动创建JavaBean
         */
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220624\\Trajectlonlat.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220624";
            autoCreatePSqlBean.generateTables = new String[]{"trajectlonlat"};
            autoCreatePSqlBean.generate();
        }else{
            System.out.println("File is Already exists !!");
        }
        /**
         * Spark执行环境，获取数据源
         */
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("PointHandle", "ERROR");
        Dataset<Row> PointDataSet = pcon.GetDataSetByProperties(spark, "select * from trajectlonlat");
        System.out.println("lines : " + PointDataSet.count());
        JavaRDD<Trajectlonlat> dataSourceRdd = PointDataSet.toJavaRDD().
                map(new RowToJavaBean<Trajectlonlat>(new GetDDL().GetGadaiteDDL(PointDataSet), Trajectlonlat.class));
        /**
         * 数据转换Geometry,并按照天数进行分组
         */
        JavaRDD<PositionAndTimeModel> CoordinateTime = dataSourceRdd.map(new TransToPosTimeFunction());
        JavaPairRDD<String, PositionAndTimeModel> datePairRDD = CoordinateTime.mapToPair(new PairFunction<PositionAndTimeModel, String, PositionAndTimeModel>() {
            @Override
            public Tuple2<String, PositionAndTimeModel> call(PositionAndTimeModel pm) throws Exception {
                String date = pm.getTimestamp().toString().split(" ")[0];
                return new Tuple2<>(date, pm);
            }
        });
        JavaRDD<Tuple2<String, Iterable<PositionAndTimeModel>>> GroupByDayRDD = datePairRDD.groupByKey().map(new Function<Tuple2<String, Iterable<PositionAndTimeModel>>, Tuple2<String, Iterable<PositionAndTimeModel>>>() {
            @Override
            public Tuple2<String, Iterable<PositionAndTimeModel>> call(Tuple2<String, Iterable<PositionAndTimeModel>> v1) throws Exception {
                List<PositionAndTimeModel> list = new ArrayList<>();
                v1._2.forEach(x -> {
                    list.add(x);
                });
                list.sort(Comparator.comparing(PositionAndTimeModel::getTimestamp));
                return new Tuple2<>(v1._1, list);
            }
        });
        /**
         * 对每天的数据进行一个统计
         */
        JavaRDD<SummaryByDayModel> summaryByDayModelJavaRDD = GroupByDayRDD.map(new SummaryByDayFunction());
        Dataset<SummaryByDayModel> summaryByDayDataSet = spark.createDataset(summaryByDayModelJavaRDD.rdd(),
                Encoders.bean(SummaryByDayModel.class));
        /**
         * 数据入库报错：
         * Caused by: org.geotools.referencing.operation.projection.ProjectionException: Latitude 40°10.0'N is too close to a pole.
         */
        pcon.PushToPSql(summaryByDayDataSet,"trajectlonlatbyday","Overwrite");
    }
}
