package GadaiteToolBaseSparkApp;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.spark.sql.types.DataTypes.*;

/**
 * made by Gadaite
 * 对不含Schema结构的Java<Row>进行Schema的填充
 */
public class JavaRowRddAddSchema implements Function<Row, Row> , Serializable {
    //  DDL.eg:  latitude:double,longitude:double,altitude:double,datetime:timestamp
    private String GadaiteDDL;

    public JavaRowRddAddSchema(String gadaiteDDL) {
        GadaiteDDL = gadaiteDDL;
    }

    public String getGadaiteDDL() {
        return GadaiteDDL;
    }

    public void setGadaiteDDL(String gadaiteDDL) {
        GadaiteDDL = gadaiteDDL;
    }

    public static Map<String , DataType> map = new HashMap<>();
    static {
        map.put("double", DoubleType);
        map.put("int", IntegerType);
        map.put("string", StringType);
        map.put("timestamp", TimestampType);
        map.put("long", LongType);
    }
    @Override
    public Row call(Row v1) throws Exception {
        String[] fieldsAndType = GadaiteDDL.split(",");
        StructType schema = new StructType();
        int length = v1.length();
        Object[] data = new Object[length];
        //  构造Schema信息,装载数据到Object[]{}
        for (int i=0;i<length;i++){
            String[] fieldAndType = fieldsAndType[i].split(":");
            schema = schema.add(fieldAndType[0],map.get(fieldAndType[1]));
            data[i] = v1.get(i);
        }
        //  创建Row
        GenericRowWithSchema genericRowWithSchema = new GenericRowWithSchema(data, schema);
        return genericRowWithSchema;
    }
}
