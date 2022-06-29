package GadaiteToolConnectDB;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Row;


import java.io.Serializable;
import java.util.*;

/**
 * made by Gadaite
 * 此处Row为带有Schema信息的Row
 * 如果没有，使用PostgresSqlRowCreate创建出Schema信息的Row出来即可
 */
public class PostgresSqlRowCreate implements Serializable, VoidFunction<Row> {
    /**
     * 部分类型还需要转换才能在postgres中使用
     */
    public static Map<String ,String> map = new HashMap<>();
    static {
        map.put("double","float8");
        map.put("int","int4");
        map.put("integer","int4");
        map.put("date","timestamp");
    }

    private JavaRDD<Row> javaRDD;
    private PostgresqlConnect PSqlConnect = new PostgresqlConnect();

    private String TableName;

    public PostgresSqlRowCreate(JavaRDD<Row> javaRDD, PostgresqlConnect PSqlConnect, String tableName) throws Exception {
        this.PSqlConnect = PSqlConnect;
        this.TableName = tableName;
        Row row = javaRDD.take(1).get(0);
        ExecCreateRow(row);
    }
    public PostgresSqlRowCreate(){}

    public PostgresqlConnect getPSqlConnect() {
        return PSqlConnect;
    }

    public void setPSqlConnect(PostgresqlConnect PSqlConnect) {
        this.PSqlConnect = PSqlConnect;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public JavaRDD<Row> getJavaRDD() {
        return javaRDD;
    }

    public void setJavaRDD(JavaRDD<Row> javaRDD) {
        this.javaRDD = javaRDD;
    }

    /**
     * made by Gadaite
     * @param javaRDD JavaRDD<Row> ,外部调用需要设置表名
     * @param TableName 创建的表名
     * @throws Exception
     */
    public void ExecCreateRow(JavaRDD<Row> javaRDD, String TableName) throws Exception {
        List<Row> oneRow = javaRDD.take(1);
        String schema = oneRow.get(0).schema().simpleString();
        String[] fieldsAndTypes = schema.substring(schema.indexOf("<") + 1, schema.indexOf(">")).split(",");
        Map<String, String> nameMapType = new HashMap<>();
        for (String field : fieldsAndTypes){
            String[] name_type = field.split(":");
            if (map.containsKey(name_type[1].toLowerCase())){
                nameMapType.put(name_type[0],map.get(name_type[1].toLowerCase()));
            }else {
                nameMapType.put(name_type[0],name_type[1].toLowerCase());
            }
        }
        StringBuffer buffer = new StringBuffer();
        StringJoiner stringJoiner = new StringJoiner(" , ");
        buffer.append("CREATE TABLE public.").append(TableName).append(" (");
        Set<Map.Entry<String, String>> entries = nameMapType.entrySet();
        for (Map.Entry<String, String> entry : entries){
            String sqlOfField = entry.getKey() + " " + entry.getValue() + " NULL";
            stringJoiner.add(sqlOfField);
        }
        String CreateTableSql = buffer.append(stringJoiner).append(" )").toString();
        PSqlConnect.ExecPSql(CreateTableSql);
    }

    /**
     * made by Gadaite
     * 内部调用，不需要设置表名
     * @param row spark-Row
     * @throws Exception
     */
    private void ExecCreateRow(Row row) throws Exception {
        String schema = row.schema().simpleString();
        String[] fieldsAndTypes = schema.substring(schema.indexOf("<") + 1, schema.indexOf(">")).split(",");
        Map<String, String> nameMapType = new HashMap<>();
        for (String field : fieldsAndTypes){
            String[] name_type = field.split(":");
            if (map.containsKey(name_type[1].toLowerCase())){
                nameMapType.put(name_type[0],map.get(name_type[1].toLowerCase()));
            }else {
                nameMapType.put(name_type[0],name_type[1].toLowerCase());
            }
        }
        StringBuffer buffer = new StringBuffer();
        StringJoiner stringJoiner = new StringJoiner(" , ");
        buffer.append("CREATE TABLE public.").append(TableName).append(" (");
        Set<Map.Entry<String, String>> entries = nameMapType.entrySet();
        for (Map.Entry<String, String> entry : entries){
            String sqlOfField = entry.getKey() + " " + entry.getValue() + " NULL";
            stringJoiner.add(sqlOfField);
        }
        String CreateTableSql = buffer.append(stringJoiner).append(" )").toString();
        PSqlConnect.ExecPSql(CreateTableSql);
    }

    /**
     * made by Gadaite
     * 外部调用，需要设置表名
     * @param row spark-Row
     * @throws Exception
     */
    public void ExecCreateRow(Row row,String TableName) throws Exception {
        String schema = row.schema().simpleString();
        String[] fieldsAndTypes = schema.substring(schema.indexOf("<") + 1, schema.indexOf(">")).split(",");
        Map<String, String> nameMapType = new HashMap<>();
        for (String field : fieldsAndTypes){
            String[] name_type = field.split(":");
            if (map.containsKey(name_type[1].toLowerCase())){
                nameMapType.put(name_type[0],map.get(name_type[1].toLowerCase()));
            }else {
                nameMapType.put(name_type[0],name_type[1].toLowerCase());
            }
        }
        StringBuffer buffer = new StringBuffer();
        StringJoiner stringJoiner = new StringJoiner(" , ");
        buffer.append("CREATE TABLE public.").append(TableName).append(" (");
        Set<Map.Entry<String, String>> entries = nameMapType.entrySet();
        for (Map.Entry<String, String> entry : entries){
            String sqlOfField = entry.getKey() + " " + entry.getValue() + " NULL";
            stringJoiner.add(sqlOfField);
        }
        String CreateTableSql = buffer.append(stringJoiner).append(" )").toString();
        PSqlConnect.ExecPSql(CreateTableSql);
    }
    /**
     * made by Gadaite
     * 先更具JavaRDD<Row>创建表，之后更新表，外部调用需要设置表名
     * @param javaRDD   JavaRDD<Row>
     * @param TableName 表名
     * @throws Exception
     */
    public void ExecMakeRow(JavaRDD<Row> javaRDD, String TableName) throws Exception {
        ExecCreateRow(javaRDD,TableName);
        new PostgresSqlRowInsert().ExecInsertRow(javaRDD,TableName);
    }

    /**
     * made by Gadaite
     * 将Row数据，创建数据表并提交到数据库表，内部调用，外部配合foreach算子
     * @param row spark-Row
     * @throws Exception
     */
    @Override
    public void call(Row row) throws Exception {
        Map<String, String> nameMapType = PSqlConnect.GetColumnNameMapType(TableName);
        StringBuffer buffer = new StringBuffer();
        StringJoiner joinerField = new StringJoiner(" , ");
        StringJoiner joinerValue = new StringJoiner(" , ");
        buffer.append("INSERT INTO public.").append(TableName).append(" (");
        for (Map.Entry<String, String> entry : nameMapType.entrySet()){
            joinerField.add(entry.getKey());
            joinerValue.add("'" + row.get(row.fieldIndex(entry.getKey())).toString() + "'");
        }
        buffer.append(joinerField.toString());
        buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
        String sqlOfInsert = buffer.toString();
        PSqlConnect.ExecPSql(sqlOfInsert);
    }
}
