package SinkApi;

import Beans.SensorReading;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;

public class ConnectKafka {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //  准备数据源
        DataStreamSource<String> dataStreamSource = env.readTextFile("F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt");
        //  转换数据源类型
        SingleOutputStreamOperator<String> sensordata = dataStreamSource.map(line -> {
            String[] strings = line.split(",");
            return new SensorReading(strings[0],new Long(strings[1]),new Double(strings[2])).toString();
        });
        //  数据写入Kafka
        //  Linux端上，开启kafka消费窗口如下：
        //  (base) [root@192 bin]# kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sparkapp
        sensordata.addSink(new FlinkKafkaProducer08<String>("192.168.1.10:9092","sparkapp",new SimpleStringSchema()));
        //  执行
        env.execute();

    }
}
