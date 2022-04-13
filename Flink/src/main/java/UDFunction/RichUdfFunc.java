package UDFunction;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class RichUdfFunc {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.10", 7777);
        dataStreamSource.map(new MyRichTrans1()).print("value--len");
        env.execute();
    }
    public static class MyRichTrans1 extends RichMapFunction<String, Tuple2<String,Integer>>{
        @Override
        public Tuple2<String, Integer> map(String value) throws Exception {
            //  这里可以获取很多的状态
            getRuntimeContext();// 运行时上下文
            getRuntimeContext().getIndexOfThisSubtask();//当前任务子任务的序号
            //'''''''''''''''''''''''''''''''''''//
            return new Tuple2<>(value,value.length());
        }
        @Override
        public void close() throws Exception {
            //  关闭连接和清空状态操作，与分区数并行度有关
            System.out.println("程序运行结束");
        }
        @Override
        public void open(Configuration parameters) throws Exception {
            //  初始化工作：定义状态，创建时进行连接，比如避免数据库反复连接，次数与分区数并行度有关
            System.out.println("程序运行开始...");
        }
    }
}
