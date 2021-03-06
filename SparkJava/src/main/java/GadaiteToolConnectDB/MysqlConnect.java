package GadaiteToolConnectDB;

import java.io.FileReader;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * made by Gadaite
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

    /**
     * made by Gadaite
     * @param resultSet
     * @return LIst结构的列名
     * @throws SQLException
     */
    public List GetResultSetColumnName(ResultSet resultSet) throws SQLException {
        ArrayList<String> columnName = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i=1;i<=columnCount;i++){
            columnName.add(metaData.getColumnName(i));
        }
        return columnName;
    }

    /**
     * made by Gadaite
     * @param resultSet
     * @return List结构的结果
     * @throws SQLException
     */
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

    /**
     * made by Gadaute
     * @param sql 执行的SQL语句
     * @throws Exception
     */
    public void ExecMysql(String sql) throws Exception {
        Properties prop = init();
        String url = prop.getProperty("url");
        String driver = prop.getProperty("driver");
        String user = prop.getProperty("username");
        String password = prop.getProperty("pwd");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();
    }

    /**
     * 获取Mysql数据库中的字段名以及对应的数据类型
     * @param resultSet 获取到的结果
     * @return Map<字段名,数据类型>
     * @throws Exception
     */
    public Map<String,String> GetColumnNameMapType(ResultSet resultSet) throws Exception{
        Map<String, String> map = new HashMap<>();
        List ColumnList = GetResultSetColumnName(resultSet);
        while (resultSet.next()){
            for (int i=1;i<=ColumnList.size();i++){
                map.put(String.valueOf(ColumnList.get(i-1)),resultSet.getMetaData().getColumnTypeName(i));
            }
            break;
        }
        return map;
    }
}
