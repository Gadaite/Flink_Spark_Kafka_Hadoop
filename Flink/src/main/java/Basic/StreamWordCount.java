package Basic;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamWordCount {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(4)设置并行度
        String path = "F:\\CodeG50\\BiGData\\Spark\\src\\main\\resources\\DataSets\\hello.txt";
        DataStream<String> dataStream = env.readTextFile(path);
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = dataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word,1));
                }
            }
        }).keyBy(0).sum(1);
        result.print();
        env.execute();
        /*来一个就输出一个数据，前面的数字就是并行的线程编号
        *   5> (hello,1)
            1> (spark,1)
            13> (flink,1)
            5> (hello,2)
            12> (nice,1)
            9> (world,1)
            13> (flink,2)
            5> (hello,3)
            1> (spark,2)
            12> (nice,2)
            12> (nice,3)
            1> (kafka,1)
        * */
    }

}
