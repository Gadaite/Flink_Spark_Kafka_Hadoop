package WorkAoutSpark.Main20220608;

import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.*;

public class TrackFunction implements Function<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>,
        Tuple2<Integer, HashMap<String,List<BrightkiteTotalcheckins>>>> {
    @Override
    public Tuple2<Integer, HashMap<String, List<BrightkiteTotalcheckins>>> call(Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> v1) throws Exception {
        /**
         * 处理/转换/合并数据类型
         * 并按照时间进行分组
         */
        Integer user = v1._1;
        HashMap<String, List<BrightkiteTotalcheckins>> hashMap = new HashMap<>();
        v1._2.forEach(data ->{
            int day = data.getTime().getDay();
            String dayStr = String.valueOf(day);
            if (!hashMap.keySet().contains(dayStr)){
                List<BrightkiteTotalcheckins> list = new ArrayList<>();
                list.add(data);
                hashMap.put(dayStr,list);
            }else {
                List<BrightkiteTotalcheckins> list = hashMap.get(dayStr);
                list.add(data);
                hashMap.put(dayStr,list);
            }
        });
        /**
         * 对list数据中的进行按照时间进行排序(倒序)
         * 更新hashmap
         */
        Set<Map.Entry<String, List<BrightkiteTotalcheckins>>> entries = hashMap.entrySet();
        for (Map.Entry<String, List<BrightkiteTotalcheckins>> entry : entries){
            List<BrightkiteTotalcheckins> value = entry.getValue();
            value.sort(Comparator.comparing(BrightkiteTotalcheckins::getTime).reversed());
            hashMap.put(entry.getKey(),value);
        }
        return new Tuple2<>(user,hashMap);
    }
}
