package WorkAoutSpark.Main20220622;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.sedona.sql.utils.SedonaSQLRegistrator;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;

import java.io.File;

/**
 * RDD的Map加上SQL语句执行写入数据到PostgresSql
 */
public class TransGeoType {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220622\\Objecttrajactory.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePsqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220622";
            autoCreatePsqlBean.generateTables = new String[]{"Objecttrajactory"};
            autoCreatePsqlBean.generate();
        }else {
            System.out.println("file is already exists!");
        }
        PostgresqlJdbcCon pjdbc = new PostgresqlJdbcCon();
        SparkSession spark = pjdbc.getSparkSesssion("Geomesa", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        spark.conf().set("spark.kryo.registrator","org.locationtech.geomesa.spark.GeoMesaSparkKryoRegistrator");
        spark.conf().set("geotools", "true");
        SedonaSQLRegistrator.registerAll(spark);
        Dataset<Row> dataset = pjdbc.GetDataSetByProperties(spark, "select * from objecttrajactory");
        dataset.show();
        dataset.printSchema();
        JavaRDD<Objecttrajactory> javaRDD = dataset.toJavaRDD().
                map(new RowToJavaBean<Objecttrajactory>(new GetDDL().GetGadaiteDDL(dataset), Objecttrajactory.class));
        Dataset<Objecttrajactory> dataset1 = spark.createDataset(javaRDD.rdd(), Encoders.bean(Objecttrajactory.class));
        dataset1.registerTempTable("view1");
        Dataset<Row> geometryDataSet = spark.sql("select lastappeared_id,ST_GeomFromWKB(`gps_line`) as gps_line from view1");
        geometryDataSet.show();
        geometryDataSet.printSchema();
        JavaRDD<Row> javaRDD1 = geometryDataSet.toJavaRDD();
        javaRDD1.take(1).forEach(x -> System.out.println(x));
        /**
         * 重写方式：下面写入的是序列化的16进制字符串
         * 追加写入：数据类型不匹配会报错
         */
//        pjdbc.PushToPSql(dataset1,"test20220622","append");
        /**
         * 使用Map并行写如数据到PG库
         */
        PostgresqlConnect pcon = new PostgresqlConnect();
        System.out.println(javaRDD.map(new Function<Objecttrajactory, Void>() {
            @Override
            public Void call(Objecttrajactory v1) throws Exception {
                String sql = "INSERT INTO public.test20220622 (lastappeared_id , gps_line) " +
                        "VALUES(" + v1.getLastappeared_id() + ", GEOMETRY('" + v1.getGps_line() + "'));";
                System.out.println(sql);
                pcon.ExecPSql(sql);
                return null;
            }
        }).count());


    }
}
