package HiveUDTF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入数据 A，B，C
 * 输出数据      A
 *              B
 *              C
 */
public class MyUDTF extends GenericUDTF {

    //  定义一个全局的输出集合类型
    //  需要几列就创建一个，取决于字段的个数
    private ArrayList<String> outputlist = new ArrayList<String>();

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //  默认输入字段名称，可以被别名覆盖
        List<String> JJMCOL = new ArrayList<String>();
        JJMCOL.add("word");
        //  输出数据类型
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        //  校验返回值
        return ObjectInspectorFactory.getStandardStructObjectInspector(JJMCOL,fieldOIs);
    }

    //  处理数据
    public void process(Object[] objects) throws HiveException {
        //  取出输入数据
        String input = objects[0].toString();
        String[] words = input.split(" ");
        //  遍历数据输出
        for (String word:words){
            outputlist.clear();
            outputlist.add(word);
            forward(outputlist);
        }


    }
    //  收尾
    public void close() throws HiveException {

    }
}
