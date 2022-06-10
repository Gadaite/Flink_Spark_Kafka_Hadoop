package WorkAoutSpark.Main20220610;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.AutoCreateMysqlBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.io.File;

public class MakeTrajectory {
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\java\\WorkAoutSpark\\Main20220610\\BrightkiteTotalcheckins.java");
        if (!file.exists()){
            AutoCreateMysqlBean autoCreateMysqlBean = new AutoCreateMysqlBean();
            AutoCreateMysqlBean.packageOutPath = "WorkAoutSpark.Main20220610";
            autoCreateMysqlBean.generateTables = new String[]{"Brightkite_totalCheckins"};
            autoCreateMysqlBean.generate();
        }else {
            System.out.println("Class is already exists！");
        }
        //  读取数据
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("Handletable", "ERRor");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark,
                "select * from Brightkite_totalCheckins where YEAR(`time`) = 2008");
        dataset.printSchema();
        /**
         *  将数据源转换为JavaRDD
         */
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<BrightkiteTotalcheckins> rdd =
                javaRDD.map(new RowToJavaBean<>(new GetDDL().GetGadaiteDDL(dataset), BrightkiteTotalcheckins.class));
        /**
         *  替换原有的location字段值为geohash值
         */
        JavaRDD<BrightkiteTotalcheckins> mapgeoRdd = rdd.map(new TransGeo());
        /**
         * 按照用户的ID进行分组
         */
        JavaPairRDD<Integer, Iterable<BrightkiteTotalcheckins>> user_rdd = mapgeoRdd.groupBy(new GroupByUser());
        /**
         * 过滤掉轨迹只有一个点的数据
         */
        JavaPairRDD<Integer, Iterable<BrightkiteTotalcheckins>> filterRdd = user_rdd.filter(new FilterPoints());
        /**
         * 按照时间字段进行排序
         */
        JavaRDD<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>> orderRDD = filterRdd.map(new OrderByTime());
        /**
         * 使用Locationtech.jts获取轨迹串，并关联上userID,获取起始时间
         */
        JavaRDD<Brightkite2008> trajectory = orderRDD.map(new TrajectoryFunction());
        trajectory.take(10).forEach(x -> System.out.println(x));
        /**
         *  数据入库,但是下面创建datset报错
         *  Exception in thread "main" java.lang.UnsupportedOperationException:
         *  Cannot have circular references in bean class,
         *  but got the circular reference of class class org.locationtech.jts.geom.Geometry
         */
//        Dataset<Row> dataFrame = spark.createDataFrame(trajectory,Brightkite2008.class);
//        dataFrame.show(10);
        /**
         * 尝试使用别的方式创建
         * Row + DDL
         * 报错：DataType linestring is not supported.
         */
        JavaRDD<Row> result = trajectory.map(new ErrorToRow());
//        Dataset<Row> dataFrame = spark.createDataFrame(result,
//                StructType.fromDDL("user Integer,startTime Timestamp,endTime Timestamp,trajectory LineString"));
        /**
         * 尝试更改RDD中的trajectory字段类型转为String
         * 创建dataset通过
         */
        JavaRDD<Row> LineToString = trajectory.map(new ErrorToStringRow());
        Dataset<Row> dataFrame = spark.createDataFrame(LineToString,
                StructType.fromDDL("user Integer,startTime Timestamp,endTime Timestamp,trajectory String"));
        dataFrame.show(10);
        /**
         * 数据入库mysql，但是trajectory字段在MySQL中的类型是text
         */
        con.PushToMySql(dataFrame,"BrightkiteTrajectory2008","overwrite");
        /**
         * 执行mysql语句更改表结构
         * 但报错：Exception in thread "main" java.sql.SQLSyntaxErrorException:
         * Column length too big for column 'trajectory' (max = 16383); use BLOB or TEXT instead
         * 那就不进行转换了，注释下面内容
         */
//        MysqlConnect connect = new MysqlConnect();
//        connect.ExecMysql("ALTER TABLE CETC10S.BrightkiteTrajectory2008 MODIFY COLUMN trajectory VARCHAR(65535)  NULL");


    }
}
