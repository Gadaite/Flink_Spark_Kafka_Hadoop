package WorkAoutSpark.Main20220609;

import scala.math.Ordering;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 为什么hashmap在遍历的时候显示是有顺序的?
 * 在1.8中的hash函数为：取对象的hashcode，然后除以 2^16（65536），然后再进行异或运算
 * 当hashcode值小于65536时，那么它的hash值就是它本身
 */
public class HashMapAndTreeMap {
    public static void main(String[] args) {
        /**
         * 使用整形的小样本数据进行测试
         * res:有序
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(2,231);
        hashMap.put(3,713987);
        hashMap.put(5,3987);
        hashMap.put(1,3587);
        hashMap.put(4,358887);
        StringBuffer Buffer = new StringBuffer();
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        hashMap.keySet().forEach(x ->Buffer.append(x).append(","));
        System.out.println(Buffer);
        for (Map.Entry<Integer, Integer> entry : entries){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("________________________________________________________________");
        /**
         * 使用整形大样本进行测试
         * 在一定的数据量下已经不能够保证顺序了
         */
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i=0;i<100000;i++){
            hashMap2.put(i,i);
        }
        Set<Map.Entry<Integer, Integer>> entries2 = hashMap2.entrySet();
        int count = -1;
        for (Map.Entry<Integer, Integer> entry : entries2){
            count = count +1;
            if (count<99970 || count>100000){
                continue;
            }else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        /**
         * 测试treeMap在大数据量下是否还能保证顺序问题
         * TreeMap能够保证顺序
         */
        TreeMap<Integer, Integer> hashMap3 = new TreeMap<>();
        for (int i=0;i<100000;i++){
            hashMap3.put(i,i);
        }
        Set<Map.Entry<Integer, Integer>> entries3 = hashMap3.entrySet();
        int Count = -1;
        for (Map.Entry<Integer, Integer> entry : entries3){
            Count = Count +1;
            if (Count<99970 || Count>100000){
                continue;
            }else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
