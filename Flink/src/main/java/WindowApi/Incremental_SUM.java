package WindowApi;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Incremental_SUM {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketTextStream = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator = socketTextStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] s = value.split(" ");
                for (String ss : s) {
                    out.collect(new Tuple2<>("tmp", new Integer(ss)));
                }
            }
        });//滑动增量计数
        streamOperator.keyBy(0).countWindow(4,2).aggregate(new AggregateFunction<Tuple2<String, Integer>, Integer, Integer>() {
            @Override
            public Integer createAccumulator() {
                return 0;
            }
            @Override
            public Integer add(Tuple2<String, Integer> value, Integer accumulator) {
                return value.f1+accumulator;
            }
            @Override
            public Integer getResult(Integer accumulator) {
                return accumulator;
            }
            @Override
            public Integer merge(Integer a, Integer b) {
                return null;
            }
        }).print("sum(4):");
        env.execute();
    }
}
