package CETC10S.ToolGetDataSet;

import CETC10S.ToolBaseSparkApp.BaseSparkENV;
import CETC10S.ToolConnectDB.MysqlJDBC;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class MysqlTableData extends MysqlJDBC{
    @Override
    public SparkSession getSparkSesssion(String appName, String loglevel) {
        return super.getSparkSesssion("SparkAPP", "ERROR");
    }
}
