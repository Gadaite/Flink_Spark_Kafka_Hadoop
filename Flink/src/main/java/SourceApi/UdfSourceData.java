package SourceApi;

import Beans.SensorReading;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;
import java.util.Random;

public class UdfSourceData {
    public static void main(String[] args) throws Exception{
        //  创建本地环境
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        //  生成数据
        DataStreamSource<SensorReading> source = env.addSource(new CreateSource());
        source.print();
        env.execute();
    }
    public static class CreateSource implements SourceFunction<SensorReading>{

        //  定义一个标识，用来控制数据的产生
        private boolean status = true;

        @Override
        public void run(SourceContext<SensorReading> ctx) throws Exception {
            //  执行起来之后就调用这个run方法
            //  ctx为当前任务的上下文
            //  定义一个随机数发生器
            Random random = new Random();
            //  设置10个温度传感器
            HashMap<String, Double> sensortmpmap = new HashMap<>();
            for(int i=0;i<10;i++){
                //  按照高斯分布随机生成
                sensortmpmap.put("sensor_"+(i+1),60 + random.nextGaussian() * 20);
            }
            while (status){
                for(String senserid : sensortmpmap.keySet()){
                    //  当前温度基础上随机波动
                    Double newtmp = sensortmpmap.get(senserid) + random.nextGaussian();
                    //  更新数据
                    sensortmpmap.put(senserid,newtmp);
                    ctx.collect(new SensorReading(senserid,System.currentTimeMillis(),newtmp));
                }

            }
            //  控制输出频率：1S,是在每个并行度下的频率
            Thread.sleep(1000L);

        }

        @Override
        public void cancel() {
            //  判断并退出
            status = false;

        }
    }
}
