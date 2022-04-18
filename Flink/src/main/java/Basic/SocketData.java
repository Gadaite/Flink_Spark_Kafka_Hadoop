package Basic;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SocketData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        //  StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //  从socket文本流读取数据
        /*
        * Linux端使用#nc - lk 7777 启动一个socket
        * */
        DataStream<String> socketdata = env.socketTextStream("192.168.1.10",7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamdata = socketdata.flatMap(new WordCount.MyFlatMapper()).keyBy(0).sum(1);
        streamdata.print();
        env.execute();

    }
}
