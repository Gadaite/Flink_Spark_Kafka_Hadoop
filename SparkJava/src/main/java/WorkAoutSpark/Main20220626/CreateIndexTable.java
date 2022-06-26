package WorkAoutSpark.Main20220626;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 由于数据量大创建新的索引表
 * 对索引分区运行代码
 */
public class CreateIndexTable {
    public static void main(String[] args) throws Exception {
        PostgresqlJdbcCon postgresqlJdbcCon = new PostgresqlJdbcCon();
        SparkSession spark = postgresqlJdbcCon.getSparkSesssion("createIndexTable", "ERROR");
        Dataset<Row> dataset = postgresqlJdbcCon.GetDataSetByProperties(spark,
                "select distinct(\"vehicleNum\") from taxidata t order by \"vehicleNum\" asc ");
        postgresqlJdbcCon.PushToPSql(dataset,"taxidataIndex","overwrite");
    }
}
