package CETC10S.ToolConnectDB;


import CETC10S.ToolBaseSparkApp.BaseSparkENV;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Properties;

public class MysqlJDBC extends BaseSparkENV implements Serializable {


    public MysqlJDBC() {
    }
    public Properties init() throws Exception{
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\mysql.properties");
        properties.load(fileReader);
        fileReader.close();
        return properties;
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
