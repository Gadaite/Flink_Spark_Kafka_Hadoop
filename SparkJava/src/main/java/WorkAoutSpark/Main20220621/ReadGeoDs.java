package WorkAoutSpark.Main20220621;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreatePSqlBean;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import GadaiteToolGeo.BinaryToGeometry;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;
import scala.Function1;
import scala.Tuple4;
//import org.locationtech.jts.io.WKBReader;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

public class ReadGeoDs implements Serializable {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220621\\Brightkitetrajectory2008.java");
        if (!file.exists()){
            AutoCreatePSqlBean autoCreatePsqlBean = new AutoCreatePSqlBean();
            AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220621";
            autoCreatePsqlBean.generateTables = new String[]{"brightkitetrajectory2008"};
            autoCreatePsqlBean.generate();
        }
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("readpg", "ERROR");
        spark.conf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from brightkitetrajectory2008");
        dataset.show();
        dataset.printSchema();
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<Brightkitetrajectory2008> brightkitetrajectory2008JavaRDD = javaRDD.map(
                new RowToJavaBean<Brightkitetrajectory2008>(new GetDDL().GetGadaiteDDL(dataset), Brightkitetrajectory2008.class));
        JavaRDD<ResultFormat> geometryRDD = brightkitetrajectory2008JavaRDD.map(new Function<Brightkitetrajectory2008, ResultFormat>() {
            @Override
            public ResultFormat call(Brightkitetrajectory2008 v1) throws Exception {
                ResultFormat resultFormat = new ResultFormat();
                String trajectory = v1.getTrajectory();
                WKBReader wkbReader = new WKBReader();
                Geometry read = wkbReader.read(WKBReader.hexToBytes(trajectory));
                resultFormat.setEndTime(v1.getEndTime());
                resultFormat.setStartTime(v1.getStartTime());
                resultFormat.setUser(v1.getUser());
                resultFormat.setTrajectory(read);
                return resultFormat;
            }
        });
        /**
         * 下面创建和写入的都说是序列化的一条一行，只有一格字段的格式
         */
        Dataset<ResultFormat> resultFormatDataset = spark.createDataset(geometryRDD.rdd(), Encoders.kryo(ResultFormat.class));
        resultFormatDataset.show();
        resultFormatDataset.printSchema();
        pcon.PushToPSql(resultFormatDataset,"test","overwrite");
        PostgresqlConnect pect = new PostgresqlConnect();
        pect.ExecPSql("drop table test");


    }
}
