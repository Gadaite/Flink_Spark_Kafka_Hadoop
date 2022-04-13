package EnvS;

import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
/*
* 创建本地的一个执行环境
* 需求idea相关的依赖
* 指定一个端口
* 目的：idea可以查看flink的web界面
* */
public class LocalAndWeb {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        //  自定义端口
        conf.setInteger(RestOptions.PORT,8050);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.10", 7777);
        dataStreamSource.print();
        env.execute();

    }
}
