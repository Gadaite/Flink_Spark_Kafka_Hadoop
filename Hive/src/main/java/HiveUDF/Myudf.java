package HiveUDF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class Myudf extends GenericUDF{
    //  初始化initialize
    //  校验参数个数类型
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors.length != 1){
            throw new UDFArgumentException("参数个数不为1");
        }
        //  返回一个校验器
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }
    //  计算evaluate
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        //  取出输入数据
        String input = deferredObjects[0].get().toString();
        //  排除空值的空指针异常
        if (input == null){
            return 0;
        }
        return input.length();
    }
    //  结合explain做执行计划优化
    public String getDisplayString(String[] strings) {
        return "";
    }
}
