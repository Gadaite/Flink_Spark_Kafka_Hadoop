package SourceApi;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import java.util.Properties;

public class FromKafka {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //  kafka连接信息
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","192.168.1.10:9092");
        properties.setProperty("zookeeper.connect","192.168.1.10:2181");
        properties.setProperty("group.id", "consumer-group");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        DataStream<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer08<String>("sparkapp", new SimpleStringSchema(), properties));
        dataStreamSource.print("INFO_ONE");
        env.execute();
    }
}
