package SourceApi;

import Beans.SensorReading;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import java.util.Arrays;

public class FromCollection {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setInteger(RestOptions.PORT,8081);
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        //  从集合读取数据fromCollection
        env.setParallelism(1);
        DataStreamSource<SensorReading> sensorReadingDataStreamSource = env.fromCollection(Arrays.asList(
                new SensorReading("sensor_1", 1547718199L, 35.8),
                new SensorReading("sensor_6", 1547718201L, 15.4),
                new SensorReading("sensor_7", 1547718202L, 6.7),
                new SensorReading("sensor_10", 1547718205L, 38.1)
        ));
        //  直接元素定义实现
        DataStream<Integer> integerDataStreamSource = env.fromElements(1, 2, 3, 4, 5, 6, 7);
        sensorReadingDataStreamSource.print().name("one").setParallelism(1);
        integerDataStreamSource.print().name("two").setParallelism(1);
        env.execute("FromCollection");
    }
}
