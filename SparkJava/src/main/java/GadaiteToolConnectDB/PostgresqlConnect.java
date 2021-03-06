package GadaiteToolConnectDB;

import java.io.FileReader;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class PostgresqlConnect implements Serializable {
    Properties properties = new Properties();
    public static String GetTableInfo = "select A.attnum," +
            "( SELECT description FROM pg_catalog.pg_description WHERE objoid = A.attrelid AND objsubid = A.attnum ) AS comment," +
            "A.attname AS field," +
            "( select typname from pg_type where oid = A.atttypid) AS type," +
            "A.atttypmod AS data_type " +
            "FROM pg_catalog.pg_attribute A " +
            "WHERE 1 = 1 " +
            "AND A.attrelid = ( SELECT oid FROM pg_class WHERE relname = 'TABLENAME') " +
            "AND A.attnum > 0 " +
            "AND NOT A.attisdropped " +
            "ORDER by A.attnum  ";
    public PostgresqlConnect() {
    }
    /**
     * 配置文件装载读取
     * @return
     * @throws Exception
     */
    public Properties init() throws Exception{
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\postgresql.properties");
        properties.load(fileReader);
        fileReader.close();
        return properties;
    }

    /**
     * 查询PSql表的结果，返回ResultSet
     * @param sql
     * @return
     * @throws Exception
     */
    public ResultSet PSqlQuery(String sql) throws Exception {
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
     * @return List结构的结果 list[Object<Map<colname,value>,...>,...]
     * @throws SQLException
     */
    public List<Map> GetResultSetMapData(ResultSet resultSet) throws SQLException {
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
    public void ExecPSql(String sql) throws Exception {
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
     * 获取Psql数据库中的字段名以及对应的数据类型
     * @param resultSet 获取到的结果,必须保证有数据存在
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

    /**
     * 从表名获取Psql数据库中的字段名以及对应的数据类型
     * @param TableName 表名
     * @return  Map<字段名,数据类型>
     * @throws Exception
     */
    public Map<String,String> GetColumnNameMapType(String TableName) throws Exception{
        Map<String, String> map = new HashMap<>();
        ResultSet resultSet = PSqlQuery(GetTableInfo.replace("TABLENAME",TableName));
        List<Map> mapList = GetResultSetMapData(resultSet);
        for (Map oneMap : mapList){
            String field = oneMap.get("field").toString();
            String type = oneMap.get("type").toString();
            map.put(field,type);
        }
        return map;
    }
}
