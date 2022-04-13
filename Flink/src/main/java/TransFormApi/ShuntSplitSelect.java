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

/*
* 分流操作只用split来看并不明显，且需要结合select进行使用
*
* */
public class ShuntSplitSelect {
    public static void main(String[] args) throws Exception{

        // create env
//        Configuration conf = new Configuration();
//        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
//        conf.setInteger(RestOptions.PORT,8081);
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);


        //  inputdata
        DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);
        socketdata.print("socketdata");


        //  to XiaoBao Type
        SingleOutputStreamOperator<XiaoBao> xiaobaosocket = socketdata.map(new MapFunction<String, XiaoBao>() {
            @Override
            public XiaoBao map(String value) throws Exception {
                String[] fields = value.split(",");
                return new XiaoBao(fields[0], new Integer(fields[1]), fields[2], new Integer(fields[3]));
            }
        });
        xiaobaosocket.print("xiaobaosocket");


        //  Split:按照score的60为界分成两条流
        SplitStream<XiaoBao> AddInfoData = xiaobaosocket.split(new OutputSelector<XiaoBao>() {
            @Override
            public Iterable<String> select(XiaoBao value) {
                //  加上一个标签戳
                return value.getScore() > 60 ? Collections.singletonList("Pass") : Collections.singletonList("Fail");
            }
        });
        AddInfoData.print("Info");

        //  使用select进行拣选,支持拣选多个
        DataStream<XiaoBao> passdata = AddInfoData.select("Pass");
        DataStream<XiaoBao> faildata = AddInfoData.select("Fail");
        DataStream<XiaoBao> moredata = AddInfoData.select("Pass", "Fail");
        passdata.print("pass");
        faildata.print("fail");
        moredata.print("moredata");
        env.execute("SplitAndSelect");
    }
    //  socket7777端口输入数据：
    /*  xiaobao,18,boy,99
        xiaojun,18,girl,45
        xiaomei,19,girl,85
        xiaohong,17,boy,100
    */
    //  idea输出情况：
    /*  socketdata> xiaobao,18,boy,99
        xiaobaosocket> XiaoBao{name='xiaobao', age=18, sex='boy', score=99}
        Info> XiaoBao{name='xiaobao', age=18, sex='boy', score=99}
        pass> XiaoBao{name='xiaobao', age=18, sex='boy', score=99}
        moredata> XiaoBao{name='xiaobao', age=18, sex='boy', score=99}
        socketdata> xiaojun,18,girl,45
        xiaobaosocket> XiaoBao{name='xiaojun', age=18, sex='girl', score=45}
        Info> XiaoBao{name='xiaojun', age=18, sex='girl', score=45}
        moredata> XiaoBao{name='xiaojun', age=18, sex='girl', score=45}
        fail> XiaoBao{name='xiaojun', age=18, sex='girl', score=45}
        socketdata> xiaomei,19,girl,85
        xiaobaosocket> XiaoBao{name='xiaomei', age=19, sex='girl', score=85}
        Info> XiaoBao{name='xiaomei', age=19, sex='girl', score=85}
        pass> XiaoBao{name='xiaomei', age=19, sex='girl', score=85}
        moredata> XiaoBao{name='xiaomei', age=19, sex='girl', score=85}
        socketdata> xiaohong,17,boy,100
        xiaobaosocket> XiaoBao{name='xiaohong', age=17, sex='boy', score=100}
        Info> XiaoBao{name='xiaohong', age=17, sex='boy', score=100}
        pass> XiaoBao{name='xiaohong', age=17, sex='boy', score=100}
        moredata> XiaoBao{name='xiaohong', age=17, sex='boy', score=100}*/
}
