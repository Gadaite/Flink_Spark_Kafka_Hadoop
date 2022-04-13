package TransFormApi;

import Beans.XiaoBao;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

import java.util.Collections;

public class ConnectAndCo {
    public static void main(String[] args) throws Exception{
        //  先创建环境，直接模拟之前的分流操作,但是分流为3种
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //  inputdata
        DataStreamSource<String> socketdata2 = env.socketTextStream("192.168.1.10", 7777);
        //  to XiaoBao Type
        SingleOutputStreamOperator<XiaoBao> xiaobaosocket2 = socketdata2.map(new MapFunction<String, XiaoBao>() {
            @Override
            public XiaoBao map(String value) throws Exception {
                String[] fields = value.split(",");
                return new XiaoBao(fields[0], new Integer(fields[1]), fields[2], new Integer(fields[3]));
            }
        });
        //  Split:按照score的60为界分成两条流
        SplitStream<XiaoBao> AddInfoData = xiaobaosocket2.split(new OutputSelector<XiaoBao>() {
            @Override
            public Iterable<String> select(XiaoBao value) {
                //  加上一个标签戳
                if(value.getScore() <60){
                    return Collections.singletonList("Fail");
                } else if(value.getScore()>=60 && value.getScore()<80){
                    return Collections.singletonList("Pass");
                }else{return Collections.singletonList("Fine");}
            }});
        //  step1:合流操作Connect
            //step1.1：选取name和score作为二元组
                //Pass对应流
        SingleOutputStreamOperator<Tuple2<String, Integer>> PassStream = AddInfoData.select("Pass")
                .map(new MapFunction<XiaoBao, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(XiaoBao value) throws Exception {
                return new Tuple2<String, Integer>(value.getName(), value.getScore());
            }
        });
                //Fine对应流
        SingleOutputStreamOperator<Tuple2<String, Integer>> FineStream = AddInfoData.select("Fine").map(new MapFunction<XiaoBao, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(XiaoBao value) throws Exception {
                return new Tuple2<String, Integer>(value.getName(), value.getScore());
            }
        });
            //step1.2：Connect连接操作，统计分数大于60的流,ConnectedStreams不能直接使用print方法
        ConnectedStreams<Tuple2<String, Integer>, Tuple2<String, Integer>> ConnectedStreams = PassStream.connect(FineStream);
        //  step2：输出结果：
            //step2.1：合流之前先进行一下映射，每条流增加一条信息
        SingleOutputStreamOperator<Object> perstreamaddinfo = ConnectedStreams.map(new CoMapFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Object>() {
            @Override
            public Object map1(Tuple2<String, Integer> value) throws Exception {
                return new Tuple3<>(value.f0, value.f1, "fighting");
            }
            @Override
            public Object map2(Tuple2<String, Integer> value) throws Exception {
                return new Tuple3<>(value.f0, value.f1, "good job");
            }
        });
        perstreamaddinfo.print("AllPath");
        env.execute();
    }
}
