package CETC10S.ToolGetDataSet;

import CETC10S.ToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.SparkSession;

public class MysqlTableData extends MysqlJdbcCon {
    @Override
    public SparkSession getSparkSesssion(String appName, String loglevel) {
        return super.getSparkSesssion("SparkAPP", "ERROR");
    }
}
