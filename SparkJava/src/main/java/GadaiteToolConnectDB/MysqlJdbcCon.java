package GadaiteToolConnectDB;


import GadaiteToolBaseSparkApp.BaseSparkENV;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Properties;

/**
 * made by Gadaite
 * 使用Spark方式连接数据，获取数据集
 */
public class MysqlJdbcCon extends BaseSparkENV implements Serializable {

    Properties properties = new Properties();
    public MysqlJdbcCon() {
    }
    public Properties init() throws Exception{
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\mysql.properties");
        properties.load(fileReader);
        fileReader.close();
        return properties;
    }

    public MysqlJdbcCon(Properties properties) {
        this.properties = properties;
    }

    /**
     *手动1输入相关参数，返回数据源
     */
    public Dataset<Row> GetDataSetByInput(SparkSession sparkSession, String url, String user, String password, String databasename, String sql_or_tablename){
        Dataset<Row> dataset = sparkSession.read().format("jdbc")
                .option("driver","com.mysql.cj.jdbc.Driver")
                .option("url", "jdbc:mysql://" + url + "/" + databasename)
                .option("user", user)
                .option("password", password)
                .option("dbtable", "( " + sql_or_tablename + " ) t").load();
        return dataset;
    }

    /**
     * 从文件配置进行加载
     * @param sparkSession
     * @param sql_or_tablename
     * @return
     */
    public Dataset<Row> GetDataSetByProperties(SparkSession sparkSession, String sql_or_tablename) throws Exception {
        init();
        Dataset<Row> dataset = sparkSession.read().format("jdbc")
                .option("driver",properties.getProperty("driver"))
                .option("url", properties.getProperty("url"))
                .option("user", properties.getProperty("username"))
                .option("password", properties.getProperty("pwd"))
                .option("dbtable", "( " + sql_or_tablename + " ) t").load();
        return dataset;
    }
    /**
     * 获取表的DDL结构
     */
    public String GetDDL(Dataset dataset){
        return dataset.schema().toDDL();
    }
    @Override
    public String ExecuteFunction() throws Exception {
        return null;
    }
}
