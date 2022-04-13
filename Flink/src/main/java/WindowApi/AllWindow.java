package WindowApi;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class AllWindow {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple1<Integer>> mapedata = socketdata.map(new MapFunction<String, Tuple1<Integer>>() {
            @Override
            public Tuple1<Integer> map(String value) throws Exception {
                return new Tuple1<>(new Integer(value));
            }
        });
        SingleOutputStreamOperator<Tuple1<Integer>> res = mapedata.timeWindowAll(Time.seconds(10)).sum(0);
        res.print("res");
        env.execute();
    }
}
