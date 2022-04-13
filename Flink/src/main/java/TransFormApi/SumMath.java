package TransFormApi;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SumMath {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setInteger(RestOptions.PORT,8081);
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        //  socket
        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);

        //  By Aggregation
        SingleOutputStreamOperator<Tuple2<String, Integer>> AggIntsocetdata = socketdata.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return new Tuple2<String, Integer>(value, 1);
            }
        });
        SingleOutputStreamOperator<Tuple2<String, Integer>> resaggsum = AggIntsocetdata.keyBy(0).sum(1);
        KeyedStream<Tuple2<String, Integer>, Tuple> keyedStream = resaggsum.keyBy(0);
        keyedStream.print("Data After KetBy");

        //  No Aggregation
        SingleOutputStreamOperator<Tuple1<Integer>> Intsocetdata = socketdata
                .map(new MapFunction<String, Tuple1<Integer>>() {
            @Override
            public Tuple1<Integer> map(String value) throws Exception {
                return new Tuple1<Integer>(new Integer(value));
            }
        });
        resaggsum.print("SUM After Aggregation");
        Intsocetdata.print("DATA Before Aggregation");
//        Intsocetdata.sum(0)这样会报错，因为sum需要聚合的一个keyedStream类型
        env.execute();
    }

}
