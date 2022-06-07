package WorkAoutSpark.Main20220605;

import GadaiteToolConnectDB.MysqlConnect;
import GadaiteToolConnectDB.MysqlJdbcCon;
import jodd.util.StringUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple5;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransCsvTwoToDbeaver {
    public static void main(String[] args) throws Exception{
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("TransData", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Dataset<Row> dataset = spark.read().format("csv").option("sep", "\t")
                .load("file:\\D:\\googledownload\\loc-brightkite_totalCheckins.txt\\Brightkite_totalCheckins.csv");
        Dataset<Row> dataset1 = dataset.withColumnRenamed("_c0", "user").withColumnRenamed("_c1", "time")
                .withColumnRenamed("_c2", "latitude")
                .withColumnRenamed("_c3", "longitude")
                .withColumnRenamed("_c4", "location");
        dataset1.show(3);
        dataset1.printSchema();
        System.out.println(dataset1.count());
        dataset1.createOrReplaceTempView("data");
        Dataset<Row> sql = spark.sql("select * from data where " +
                "user is not null " +
                "and time is not null " +
                "and latitude is not null " +
                "and longitude is not null " +
                "and location is not null limit 100");
        System.out.println(sql.count());
        spark.sqlContext().udf().register("UDFF",new TransField(), DataTypes.StringType);
        Dataset<Row> sqlres = spark.sql("select user,UDFF(time) as time,latitude,longitude,location from data");
        sqlres.show(10);
//        con.PushToMySql(sqlres,"Brightkite_totalCheckins","overwrite");
        /**
         * 执行表结构，更新Mysql数据类型
         */
        MysqlConnect connect = new MysqlConnect();
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_totalCheckins MODIFY COLUMN `user` int NULL");
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_totalCheckins MODIFY COLUMN `time` TIMESTAMP NULL");
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_totalCheckins MODIFY COLUMN latitude double NULL");
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_totalCheckins MODIFY COLUMN longitude double NULL");
        connect.ExecMysql("ALTER TABLE CETC10S.Brightkite_totalCheckins MODIFY COLUMN location varchar(64) NULL");


    }

}
