package Basic;

import TransFormApi.Basic;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class Tuple3_Tuple2 {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
        conf.setInteger(RestOptions.PORT, 8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<Tuple3<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>> tuple3_2socketdata = socketdata.map(new Tuple3_2());
//        tuple3_2socketdata.map(line ->{
//            Arrays arr = line.getField(0);
//            return arr;
//        }).print();
        tuple3_2socketdata.print();
        env.execute();


    }
    public static class Tuple3_2 implements MapFunction<String, Tuple3<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>>{

        @Override
        public Tuple3<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>> map(String value) throws Exception {
            String[] arr = value.split(" ");
            int[] res = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                res[i] = Integer.parseInt(arr[i]);
            }
            return new Tuple3<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>
                    (new Tuple2<String, Integer>("A1",res[0]),new Tuple2<String, Integer>("A2",res[1]),new Tuple2<String, Integer>("A3",res[2]));
        }
    }
}