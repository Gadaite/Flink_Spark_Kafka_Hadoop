package TransFormApi;

import Beans.XiaoBao;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Collections;

public class UnionMoreStream {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.10", 7777);
        SingleOutputStreamOperator<XiaoBao> xiaoBaoSingleOutputStreamOperator = dataStreamSource.map((MapFunction<String, XiaoBao>) value -> {
            String[] s = value.split(",");
            return new XiaoBao(s[0], new Integer(s[1]), s[2], new Integer(s[3]));
        });
        SplitStream<XiaoBao> xiaoBaoSplitStream = xiaoBaoSingleOutputStreamOperator.split((OutputSelector<XiaoBao>) value -> {
            if (value.getScore() < 60) {
                return Collections.singletonList("Fail");
            } else if (value.getScore() >= 60 && value.getScore() < 80) {
                return Collections.singletonList("Pass");
            } else {
                return Collections.singletonList("Fine");
            }
        });
        DataStream<XiaoBao> fail = xiaoBaoSplitStream.select("Fail");
        DataStream<XiaoBao> fine = xiaoBaoSplitStream.select("Fine");
        DataStream<XiaoBao> pass = xiaoBaoSplitStream.select("Pass");

        //  union联合多条流,但是必须保证数据类型相同
        DataStream<XiaoBao> union = fail.union(fine, pass);
        union.print("union");
        env.execute();
    }
}
