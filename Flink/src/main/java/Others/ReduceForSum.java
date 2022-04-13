package Others;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ReduceForSum {
    public static void main(String[] args) throws Exception{
//        Configuration conf = new Configuration();
//        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
//        conf.setInteger(RestOptions.PORT,8081);
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> reducesum = socketdata.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return new Tuple2<String, Integer>("Result", new Integer(value));
            }
        }).keyBy(0).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                return new Tuple2<String, Integer>(value1.f0, value1.f1 + value2.f1);
            }
        });
        reducesum.print();
        env.execute("reduceforsum");
    }
}
