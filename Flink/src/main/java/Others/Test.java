package Others;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Test {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String path = "F:\\CodeG50\\BiGData\\Spark\\src\\main\\resources\\DataSets\\hello.txt";
        DataStreamSource<String> inputdata = env.readTextFile(path);
        inputdata.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(new Tuple2<String, Long>(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG)).print();
        env.execute();
    }
}
