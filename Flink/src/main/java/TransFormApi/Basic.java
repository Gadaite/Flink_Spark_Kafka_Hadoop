package TransFormApi;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Basic {
    public static void main(String[] args) throws Exception{
        //  创建本地环境
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        env.setParallelism(1);

        //  读取数据
        String path = "F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt";
        DataStreamSource<String> inputdata = env.readTextFile(path);

        //  Map转换 String转换成长度输出
        SingleOutputStreamOperator<Integer> mapfunc = inputdata.map(new MapFunction<String, Integer>() {
            @Override
            public Integer map(String value) throws Exception {
                return value.length();
            }
        });

        //  FlatMap转换 按照 ， 切分字段
        SingleOutputStreamOperator<String> flatmapfunc = inputdata.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] words = value.split(",");
                for (String word : words) {
                    out.collect(word);
                }
            }
        });

        //  filter过滤，筛选sensor_1开头的id对应的数据
        SingleOutputStreamOperator<String> filterstartwith = inputdata.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return value.startsWith("sensor_1");
            }
        });

        //  打印输出
        mapfunc.print().name("map");
        flatmapfunc.print().name("flatmap");
        filterstartwith.print().name("filter");

        //  执行
        env.execute();
    }
}
