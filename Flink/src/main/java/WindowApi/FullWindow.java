package WindowApi;

import org.apache.commons.collections.IteratorUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.IterableUtils;

public class FullWindow {
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
        flatMapdata.keyBy(0).timeWindow(Time.seconds(15)).apply(new WindowFunction<Tuple2<String, Integer>, Tuple3<String,Integer,Long>, Tuple, TimeWindow>() {
            @Override
            public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Integer>> input, Collector<Tuple3<String, Integer,Long>> out) throws Exception {
                int size = IteratorUtils.toList(input.iterator()).size();
                out.collect(new Tuple3<String, Integer,Long>(tuple.getField(0),size,window.getEnd()));
            }
        }).print("Info");
        env.execute();
    }
}
