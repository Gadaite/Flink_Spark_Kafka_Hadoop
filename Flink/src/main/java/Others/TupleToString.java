package Others;

import org.apache.flink.api.java.tuple.Tuple3;

public class TupleToString {
    public static void main(String[] args) {
        Tuple3<Integer, Integer, Integer> t = new Tuple3<Integer, Integer, Integer>(1,2,3);
        System.out.println(t);//    (1,2,3)
        Object field = t.getField(0);
        System.out.println(field);//    1

        Tuple3<Integer, Integer, Integer> t1 = new Tuple3<>(1, 2, 3);
        Tuple3<Integer, Integer, Integer> t2 = new Tuple3<>(4, 5, 6);
        Tuple3<Integer, Integer, Integer> t3 = new Tuple3<>(7, 8, 9);
        Tuple3<Tuple3<Integer, Integer, Integer>, Tuple3<Integer, Integer, Integer>, Tuple3<Integer, Integer, Integer>> t33 = new Tuple3<>(t1,t2,t3);
        System.out.println(t33);//  ((1,2,3),(4,5,6),(7,8,9))
        Object field1 = t33.getField(0);
        System.out.println(field1);//   (1,2,3)
        String s = field1.toString();
        System.out.println(s);//    1
        String s1 = t33.toString();
        System.out.println(s1);//   ((1,2,3),(4,5,6),(7,8,9))


    }

}
