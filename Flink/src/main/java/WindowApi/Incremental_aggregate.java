package WindowApi;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class Incremental_aggregate {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketTextStream = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> flatMapdata = socketTextStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] s = value.split(" ");
                for (String ss : s) {
                    out.collect(new Tuple2<String, Integer>(ss, 1));
                }
            }
        });
        SingleOutputStreamOperator<Integer> aggregate = flatMapdata.keyBy(0).timeWindow(Time.seconds(15)).aggregate(new AggregateFunction<Tuple2<String, Integer>, Integer, Integer>() {
            @Override
            public Integer createAccumulator() {//  创建累加器初始值
                return 0;
            }
            @Override
            public Integer add(Tuple2<String, Integer> value, Integer accumulator) { //  累加方式
                return accumulator + value.f1;
            }
            @Override
            public Integer getResult(Integer accumulator) { //  输出结果这里导致出现只有整数无法获取到当前的单词名称
                return accumulator;
            }
            @Override
            public Integer merge(Integer a, Integer b) {//  主要用于sessionWindow，合并操作
                return a + b;
            }
        });
        aggregate.print("res");
        env.execute();
    }
}
