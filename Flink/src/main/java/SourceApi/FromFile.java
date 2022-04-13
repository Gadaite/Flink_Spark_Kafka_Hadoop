package SourceApi;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FromFile {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String path = "F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt";
        DataStreamSource<String> dataStreamSource = env.readTextFile(path);
        dataStreamSource.print();
        env.execute();

    }
}
