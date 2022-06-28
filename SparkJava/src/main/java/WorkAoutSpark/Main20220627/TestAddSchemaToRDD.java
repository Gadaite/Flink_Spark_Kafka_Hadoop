package WorkAoutSpark.Main20220627;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.JavaRowRddAddSchema;
import GadaiteToolConnectDB.PostgresSqlInsert;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;

import java.sql.Timestamp;

public class TestAddSchemaToRDD {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("RowAddSchema", "ERROR");
        Dataset<Row> dataset = pcon.GetDataSetByProperties(spark, "select * from trajectlonlathash9 limit 10");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        JavaRDD<Row> RowNotSchema = javaRDD.map(new Function<Row, Row>() {
            @Override
            public Row call(Row v1) throws Exception {
                double latitude = v1.getDouble(v1.fieldIndex("latitude"));
                double longitude = v1.getDouble(v1.fieldIndex("longitude"));
                double altitude = v1.getDouble(v1.fieldIndex("altitude"));
                Timestamp datetime = v1.getTimestamp(v1.fieldIndex("datetime"));
                Row row = RowFactory.create(latitude, longitude, altitude, datetime);
                return row;
            }
        });
        String gadaiteDDL = new GetDDL().GetGadaiteDDL(dataset);
        System.out.println(gadaiteDDL);
        /*
        RowNotSchema.foreach(x ->x.getTimestamp(x.fieldIndex("datetime")));
        Caused by: java.lang.UnsupportedOperationException: fieldIndex on a Row without schema is undefined.
         */
        JavaRDD<Row> ContainSchemeRDD = RowNotSchema.map(new JavaRowRddAddSchema(gadaiteDDL));
        System.out.println(ContainSchemeRDD.collect());
        /**
         * 测试数据入库,测试先前自定义的工具类
         */
        new PostgresSqlInsert().ExecInsert(ContainSchemeRDD,"test20220627");

        PostgresqlConnect pSqlConnect = new PostgresqlConnect();
        ContainSchemeRDD.foreach(new PostgresSqlInsert(pSqlConnect,"test20220627"));
    }
}
