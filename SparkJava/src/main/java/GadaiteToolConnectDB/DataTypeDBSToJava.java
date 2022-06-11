package GadaiteToolConnectDB;

import java.util.HashMap;
import java.util.Map;

/**
 * made by Gadaite
 * 用于SQL数据类型转换为Java类型
 * 后续不断补充
 */
public class DataTypeDBSToJava {
    public static Map<String ,String> map = new HashMap<>();
    static {
        /**
         * 下面包括但不限于mysql
         */
        map.put("smallint", "java.lang.Integer");
        map.put("mediumint", "java.lang.Integer");
        map.put("integer", "java.lang.Integer");
        map.put("tinyint", "java.lang.Short");
        map.put("int", "java.lang.Integer");
        map.put("bigint", "java.lang.Long");
        map.put("float", "java.lang.Float");
        map.put("double", "java.lang.Double");
        map.put("decimal", "java.math.BigDecimal");
        map.put("date", "java.util.Date");
        map.put("datetime", "java.util.Date");
        map.put("timestamp", "java.util.Date");
        map.put("time", "java.util.Date");
        map.put("year", "java.util.Date");
        map.put("char", "java.lang.String");
        map.put("varchar", "java.lang.String");
        map.put("binary", "byte[]");
        map.put("varbinary", "byte[]");
        map.put("tinyblob", "byte[]");
        map.put("tinytext", "java.lang.String");
        map.put("blob", "byte[]");
        map.put("text", "java.lang.String");
        map.put("mediumblob", "byte[]");
        map.put("mediumtext", "java.lang.String");
        map.put("longblob", "byte[]");
        map.put("longtext", "java.lang.String");
        map.put("enum", "java.lang.String");
        map.put("set", "java.lang.String");
        map.put("bit", "java.lang.Boolean");

        /**
         * 为PostgresSql新增的部分
         */
        map.put("int4", "java.lang.Integer");
        map.put("geometry","java.lang.String");
    }
}
