package GadaiteToolConnectDB;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * made by Gadaite
 * @param <T> JavaClass Entity
 */
public class PostgresSqlEntityCreate<T> implements Serializable, VoidFunction<T> {
    /**
     * 部分类型还需要转换才能在postgres中使用
     */
    public static Map<String ,String> map = new HashMap<>();
    static {
        map.put("double","float8");
        map.put("int","int4");
        map.put("integer","int4");
        map.put("date","timestamp");
        map.put("string","text");
    }
    private Class<T> clazz;
    private PostgresqlConnect PSqlConnect = new PostgresqlConnect();
    private String TableName;

    /**
     * made by Gadaite
     * @param clazz JavaRDD内的对象的封装类型
     * @param PSqlConnect   PostgresqlConnect对象
     * @param tableName 表名
     */
    public PostgresSqlEntityCreate(Class<T> clazz, PostgresqlConnect PSqlConnect, String tableName) throws Exception {
        this.clazz = clazz;
        this.PSqlConnect = PSqlConnect;
        TableName = tableName;
        //  在初始化时执行创建表，重复创建必定报错
        ExecCreateEntity(this.clazz.newInstance());
    }

    public PostgresSqlEntityCreate(){}

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
     * 根据RDD信息直接创建表，可外部调用
     * @param javaRDD JavaRDD<T>
     * @param TableName 表名
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void ExecCreateEntity(JavaRDD<T> javaRDD, String TableName) throws Exception {
        Map<String, String> nameMapType = new HashMap<>();
        Field[] fields = javaRDD.take(1).get(0).getClass().getDeclaredFields();
        for (Field field : fields){
            if (map.containsKey(field.getType().getSimpleName().toLowerCase())){
                nameMapType.put(field.getName(),map.get(field.getType().getSimpleName().toLowerCase()));
            }else {
                nameMapType.put(field.getName(),field.getType().getSimpleName().toLowerCase());
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
     * 先更具JavaRDD<Row>创建表，之后更新表，外部调用
     * @param javaRDD   JavaRDD<T>
     * @param TableName 表名
     * @throws Exception
     */
    public void ExecMakeEntity(JavaRDD<T> javaRDD, String TableName) throws Exception {
        ExecCreateEntity(javaRDD,TableName);
        new PostgresSqlEntityInsert().ExecInsertEntity(javaRDD,TableName);
    }

    /**
     * made by Gadaite
     * 使用泛型创建postgres表，内部调用不需要设置表名
     * @param t JavaClass
     * @throws Exception
     */
    private void ExecCreateEntity(T t) throws Exception {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Map<String, String> nameMapType = new HashMap<>();
        for (Field field : declaredFields){
            if (map.containsKey(field.getType().getSimpleName().toLowerCase())){
                nameMapType.put(field.getName(),map.get(field.getType().getSimpleName().toLowerCase()));
            }else {
                nameMapType.put(field.getName(),field.getType().getSimpleName().toLowerCase());
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
     * 使用泛型创建postgres表，外部调用需要设置表名
     * @param t JavaClass
     * @throws Exception
     */
    public void ExecCreateEntity(T t,String TableName) throws Exception {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Map<String, String> nameMapType = new HashMap<>();
        for (Field field : declaredFields){
            if (map.containsKey(field.getType().getSimpleName().toLowerCase())){
                nameMapType.put(field.getName(),map.get(field.getType().getSimpleName().toLowerCase()));
            }else {
                nameMapType.put(field.getName(),field.getType().getSimpleName().toLowerCase());
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
     * JavaClass类型的RDD直接创建表并上传到数据库,内部调用
     * @param t JavaClass
     * @throws Exception
     */
    @Override
    public void call(T t) throws Exception {
        //  执行插入数据
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
            joinerValue.add("'" + nameMapValue.get(entry.getKey()) + "'");
        }
        buffer.append(joinerField.toString());
        buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
        String sqlOfInsert = buffer.toString();
        PSqlConnect.ExecPSql(sqlOfInsert);
    }
}
