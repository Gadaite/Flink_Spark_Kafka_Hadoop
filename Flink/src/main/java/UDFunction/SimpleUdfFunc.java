package UDFunction;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SimpleUdfFunc {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.10", 7777);
        dataStreamSource.map(new MyTrans1()).print("value--len");
        env.execute();

    }
    public static class MyTrans1 implements MapFunction<String,Tuple2<String,Integer>>{

        @Override
        public Tuple2<String, Integer> map(String value) throws Exception {
            return new Tuple2<String, Integer>(value,value.length());
        }
    }
}
