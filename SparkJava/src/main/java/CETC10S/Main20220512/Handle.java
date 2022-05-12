package CETC10S.Main20220512;

import org.apache.spark.api.java.function.FlatMapFunction;

import java.io.Serializable;
import java.util.Iterator;

public class Handle implements Serializable, FlatMapFunction {

    /**
     * 定义方法：将某一列进行合并
     * @param o
     * @return
     * @throws Exception
     */

    @Override
    public Iterator call(Object o) throws Exception {
        return null;
    }
}
