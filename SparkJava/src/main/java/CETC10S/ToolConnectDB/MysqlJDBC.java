package CETC10S.ToolConnectDB;


import CETC10S.ToolBaseSparkApp.BaseSparkENV;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Properties;

public class MysqlJDBC extends BaseSparkENV implements Serializable {

    Properties properties = new Properties();
    public MysqlJDBC() {
    }
    public Properties init() throws Exception{
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\mysql.properties");
        properties.load(fileReader);
        fileReader.close();
        return properties;
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
                .option("dbtable", sql_or_tablename).load();
        return dataset;
    }

    /**
     * 从文件配置进行加载
     * @param sparkSession
     * @param sql_or_tablename
     * @return
     */
    public Dataset<Row> GetDataSetByProperties(SparkSession sparkSession, String sql_or_tablename){
        Dataset<Row> dataset = sparkSession.read().format("jdbc")
                .option("driver",properties.getProperty("driver"))
                .option("url", properties.getProperty("url"))
                .option("user", properties.getProperty("username"))
                .option("password", properties.getProperty("pwd"))
                .option("dbtable", sql_or_tablename).load();
        return dataset;
    }
    /**
     * 获取表的DDL结构
     */
    public String GetDDL(Dataset dataset){
        return dataset.schema().toDDL();
    }
    /**
     * x下面是测试时使用的代码
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        MysqlJDBC mysqlJDBC = new MysqlJDBC();
        mysqlJDBC.init();
        System.out.println(mysqlJDBC.init().getProperty("driver"));
        System.out.println(mysqlJDBC.init().getProperty("url"));
        System.out.println(mysqlJDBC.init().getProperty("pwd"));
        System.out.println(mysqlJDBC.init().getProperty("username"));
    }


    @Override
    public String ExecuteFunction() throws Exception {
        return null;
    }
}
