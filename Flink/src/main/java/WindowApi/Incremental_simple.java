package WindowApi;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
/*
* 使用sum实现简单增量的计算统计
* 滚动方式
* 时间开窗
* */

public class Incremental_simple {
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
        SingleOutputStreamOperator<Tuple2<String, Integer>> sumdata = flatMapdata.keyBy(0).timeWindow(Time.seconds(10)).sum(1);
        sumdata.print();
        env.execute();
    }
}
