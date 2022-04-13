package Others;

import Beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Reparation {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<String> dataStreamSource = env.readTextFile("F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt");

        //  origin
        dataStreamSource.print("origin-part");
        Thread.sleep(5000);
        //  shuffer
        dataStreamSource.shuffle();
        dataStreamSource.print("shuffer----------part");
        Thread.sleep(5000);
        //  keyby：相同的key必定在一个分区
        dataStreamSource.map(new MapFunction<String, SensorReading>() {
            @Override
            public SensorReading map(String value) throws Exception {
                String[] strings = value.split(",");
                return new SensorReading(strings[0],new Long(strings[1]),new Double(strings[2]));
            }
        }).keyBy("id").print("keyby------------result");
        Thread.sleep(5000);
        //  global：全放在一个分区
        dataStreamSource.global().print("global-------------------------------------res");
        Thread.sleep(5000);
        env.execute();

    }
}
