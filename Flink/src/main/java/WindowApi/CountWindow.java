package WindowApi;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
/*A,5
B,1
A,4
A,3
B,2
B,3
B,4
A,1
A,2
B,5
*/
public class CountWindow {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketTextStream = env.socketTextStream("192.168.1.10", 7777);//   输入数据类型如（A，1）/n (A,2),数字表示值
        env.setParallelism(1);
        SingleOutputStreamOperator<Tuple2<String, Double>> mapdata = socketTextStream.map(new MapFunction<String, Tuple2<String, Double>>() {
            @Override
            public Tuple2<String, Double> map(String value) throws Exception {
                String[] s = value.split(",");
                return new Tuple2<>(s[0], new Double(s[1]));
            }
        });
        //  前方mapdata数据类型为2元组（字符串，字符串对应值(Double)）  计算平均值,下面两个参数表示滑动窗口,输出字符串以及该窗口下的 对应 的平均值
        mapdata.keyBy(0).countWindow(2,1).aggregate(new AggregateFunction<Tuple2<String, Double>, // 原有数据字符串以及对应值
             Tuple3<String, Double, Double>,// 分别存放原有字符串，和值，以及当前个数
             Tuple2<String, Double>>() {// 存放输出的key和平均值
             @Override //   初始化（空字符串，对应值总和，当前值）
             public Tuple3<String, Double, Double> createAccumulator() {
                 return new Tuple3<>(null,0.0,0.0);
             }
             @Override //   迭代规则(字符串)
             public Tuple3<String, Double, Double> add(Tuple2<String, Double> value, Tuple3<String, Double, Double> accumulator) {
                 //keyby之后可能相同的一个分区有相同的key,是否会有影响？？？如果出现value.f0 != accumulator.f0
                 return  new Tuple3<>(value.f0, value.f1 + accumulator.f1, accumulator.f2 +1.0);
             }
             @Override
             public Tuple2<String, Double> getResult(Tuple3<String, Double, Double> accumulator) {
                 return new Tuple2<>(accumulator.f0,accumulator.f1/accumulator.f2);
             }
             @Override
             public Tuple3<String, Double, Double> merge(Tuple3<String, Double, Double> a, Tuple3<String, Double, Double> b) {
                 return null;
             }
         }).print();
        env.execute();
    }
}
