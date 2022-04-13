package SourceApi;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FromElements {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Tuple2<String, Integer>> tuple2DataStreamSource = env.fromElements(Tuple2.of("a", 1), Tuple2.of("b", 1), Tuple2.of("b", 3));
        tuple2DataStreamSource.keyBy(x ->x.f0).sum(1).print();
        env.execute();
    }
}
