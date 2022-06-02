package GadaiteToolBaseSparkApp;


import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * made by Gadaite
 * 用于Spark的Row类型RDD,转换为T类型的JavaBean的RDD
 * @param <T>
 */
public class RowToJavaBean<T> implements Serializable, Function<Row,T> {
    public String ddl;
    public Class<T> clazz;

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public RowToJavaBean(String ddl, Class<T> clazz) {
        this.ddl = ddl;
        this.clazz = clazz;
    }
    public RowToJavaBean(){}
    @Override
    public T call(Row v1) throws Exception {
        /**
         * 获取Row类型的HashMap结构：字段-->类型
         * 另存键的值到List列表
         */
        String[] fields_types = ddl.split(",");
        HashMap<String, String> hashMap = new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (String field_type : fields_types){
            String[] strings = field_type.split(":");
            hashMap.put(strings[0],strings[1]);
            keys.add(strings[0]);
        }
        /**
         * 实例化泛型T
         */
        T Info = clazz.newInstance();
        Field[] declaredFields = Info.getClass().getDeclaredFields();
        for (int i=0;i<declaredFields.length;i++){
            Field field = declaredFields[i];
            field.setAccessible(true);
            String FeildName = declaredFields[i].getName();
            String simpleName = declaredFields[i].getType().getSimpleName();
            if (simpleName.equalsIgnoreCase("int") || simpleName.equalsIgnoreCase("integer")){
                field.set(Info,v1.getInt(v1.fieldIndex(FeildName)));
            }else if (simpleName.equalsIgnoreCase("string")){
                field.set(Info,v1.getString(v1.fieldIndex(FeildName)));
            }else if (simpleName.equalsIgnoreCase("double")){
                field.set(Info,v1.getDouble(v1.fieldIndex(FeildName)));
            }else if (simpleName.equalsIgnoreCase("decimal")){
                field.set(Info,v1.getDecimal(v1.fieldIndex(FeildName)));
            }else if (simpleName.equalsIgnoreCase("Date")){
                field.set(Info,v1.getTimestamp(v1.fieldIndex(FeildName)));
            }
        }
        return Info;
    }

}
