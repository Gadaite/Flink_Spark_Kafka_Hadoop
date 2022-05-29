package WorkAoutSpark.Main20220529;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.sql.SparkSession;

public class FlightsDDL {
    public String GetDDL() throws Exception {
        /**
         * 使用Spark读取一行数据，获得DDL即可
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        con.init();
        SparkSession sesssion = con.getSparkSesssion("HandleTable", "ERROR");
        String ddl = con.GetDataSetByProperties(sesssion, "select * from flights limit 1").schema().toDDL();
        sesssion.stop();
        return ddl;
    }

}
