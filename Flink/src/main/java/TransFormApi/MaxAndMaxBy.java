package TransFormApi;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.FormattingMapper;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
public class MaxAndMaxBy {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        env.setParallelism(1);

        // data from socket
        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);

        // data to tuple3
        SingleOutputStreamOperator<Tuple3<Integer, Integer, Integer>> datatuple3 = socketdata.map(new MapFunction<String, Tuple3<Integer, Integer, Integer>>() {
            @Override
            public Tuple3<Integer, Integer, Integer> map(String s) throws Exception {
                String[] arr = s.split(" ");
                return new Tuple3<Integer, Integer, Integer>(new Integer(arr[0]), new Integer(arr[1]), new Integer(arr[2]));
            }
        });
        //  keyBy:
        KeyedStream<Tuple3<Integer, Integer, Integer>, Tuple> keyedStream = datatuple3.keyBy(0);
        //  max：
        SingleOutputStreamOperator<Tuple3<Integer, Integer, Integer>> max = keyedStream.max(0);
        //  maxBy:
        SingleOutputStreamOperator<Tuple3<Integer, Integer, Integer>> maxby = keyedStream.maxBy(0);

        keyedStream.print("keyBy");
        max.print("max");
        maxby.print("maxBy");

        env.execute();

    }
}
