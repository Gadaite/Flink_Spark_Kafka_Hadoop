package WindowApi;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
/*
* 失败代码与设计期望输出结果不符
* */
public class Incremental_aggregateformat {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketTextStream = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> flatMapdata = socketTextStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] s = value.split(" ");
                for (String ss : s) {
                    out.collect(new Tuple2<String, Integer>(ss, 1)); }
            }
        });
        flatMapdata.keyBy(0).timeWindow(Time.seconds(15)).aggregate(new AggregateFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> createAccumulator() { return new Tuple2<>(null,0); }
            @Override
            public Tuple2<String, Integer> add(Tuple2<String, Integer> value, Tuple2<String, Integer> accumulator) {
                return new Tuple2<String, Integer>(value.f0,value.f1+accumulator.f1);
            }
            @Override
            public Tuple2<String, Integer> getResult(Tuple2<String, Integer> accumulator) {
                return new Tuple2<String, Integer>(accumulator.f0,accumulator.f1);
            }
            @Override
            public Tuple2<String, Integer> merge(Tuple2<String, Integer> a, Tuple2<String, Integer> b) { return null; }
        }).print("Info");
        env.execute();
    }
}
