package GadaiteToolConnectDB;

import java.io.FileReader;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * made by yangyi
 */
public class MysqlConnect implements Serializable {
    Properties properties = new Properties();
    public MysqlConnect() {
    }
    /**
     * 配置文件装载读取
     * @return
     * @throws Exception
     */
    public Properties init() throws Exception{
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\mysql.properties");
        properties.load(fileReader);
        fileReader.close();
        return properties;
    }

    /**
     * 查询Mysql表的结果，返回ResultSet
     * @param sql
     * @return
     * @throws Exception
     */
    public ResultSet MysqlQuery(String sql) throws Exception {
        Properties prop = init();
        String url = prop.getProperty("url");
        String driver = prop.getProperty("driver");
        String user = prop.getProperty("username");
        String password = prop.getProperty("pwd");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeQuery();
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public List GetResultSetColumnName(ResultSet resultSet) throws SQLException {
        ArrayList<String> columnName = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i=1;i<=columnCount;i++){
            columnName.add(metaData.getColumnName(i));
        }
        return columnName;
    }
    public List<Map> GetREsultSetMapData(ResultSet resultSet) throws SQLException {
        List<Map> mapdata = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            Map map = new HashMap();
            for (int i=1;i<=columnCount;i++){
                map.put(metaData.getColumnName(i),resultSet.getObject(i));
            }
            mapdata.add(map);
        }
        return mapdata;
    }
}
