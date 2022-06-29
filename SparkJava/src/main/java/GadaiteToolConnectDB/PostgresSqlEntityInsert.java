package GadaiteToolConnectDB;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.sources.In;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class PostgresSqlEntityInsert<T> implements Serializable, VoidFunction<T> {

    private Class<T> clazz;
    private PostgresqlConnect PSqlConnect = new PostgresqlConnect();
    private String TableName;

    /**
     * made by Gadaite
     * @param clazz JavaRDD内的对象的封装类型
     * @param PSqlConnect   PostgresqlConnect对象
     * @param tableName 表名
     */
    public PostgresSqlEntityInsert(Class<T> clazz, PostgresqlConnect PSqlConnect, String tableName) {
        this.clazz = clazz;
        this.PSqlConnect = PSqlConnect;
        TableName = tableName;
    }

    public PostgresSqlEntityInsert(){}

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

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

    /**
     * 使用JavaRDD<Bean>.foreach执行对数据的insert操作
     * @param t 实体类的数据封装的类
     * @throws Exception
     */
    @Override
    public void call(T t) throws Exception {
        T Info = clazz.newInstance();
        Field[] declaredFields = Info.getClass().getDeclaredFields();
        String s = t.toString();
        String[] split = s.substring(s.indexOf("[") + 1, s.indexOf("]")).split(",");
        Map<String, String> nameMapValue = new HashMap<>();
        for (String str : split){
            String[] fieldAndValue = str.replaceFirst(" ", "").split("=");
            nameMapValue.put(fieldAndValue[0],fieldAndValue[1]);
        }
        Map<String, String> nameMapType = new HashMap<>();
        for (Field field : declaredFields){
            nameMapType.put(field.getName(),field.getType().getSimpleName());
        }
        StringBuffer buffer = new StringBuffer();
        StringJoiner joinerField = new StringJoiner(" , ");
        StringJoiner joinerValue = new StringJoiner(" , ");
        buffer.append("INSERT INTO public.").append(TableName).append(" (");
        for (Map.Entry<String, String> entry : nameMapType.entrySet()){
            joinerField.add(entry.getKey());
//            joinerValue.add(entry.getValue() + "('" + nameMapValue.get(entry.getKey()) + "')");
            joinerValue.add("'" + nameMapValue.get(entry.getKey()) + "'");
        }
        buffer.append(joinerField.toString());
        buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
        String sqlOfInsert = buffer.toString();
        PSqlConnect.ExecPSql(sqlOfInsert);
    }

    public void ExecInsertEntity(JavaRDD<T> javaRDD, String TableName) throws Exception {
        Map<String, String> nameMapType = PSqlConnect.GetColumnNameMapType(TableName);
        String s = javaRDD.take(1).get(0).toString();
        String[] split = s.substring(s.indexOf("[") + 1, s.indexOf("]")).split(",");
        Map<String, String> nameMapValue = new HashMap<>();
        for (String str : split){
            String[] fieldAndValue = str.replaceFirst(" ", "").split("=");
            nameMapValue.put(fieldAndValue[0],fieldAndValue[1]);
        }
        javaRDD.foreach(x ->{
            StringBuffer buffer = new StringBuffer();
            String s1 = x.toString();
            StringJoiner joinerField = new StringJoiner(" , ");
            StringJoiner joinerValue = new StringJoiner(" , ");
            buffer.append("INSERT INTO public.").append(TableName).append(" (");
            for (Map.Entry<String, String> entry : nameMapType.entrySet()){
                joinerField.add(entry.getKey());
                joinerValue.add("'" + nameMapValue.get(entry.getKey()) + "'");
            }
            buffer.append(joinerField.toString());
            buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
            String sqlOfInsert = buffer.toString();
            PSqlConnect.ExecPSql(sqlOfInsert);
        });
    }
}
