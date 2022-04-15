package TableApi;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//  不能使用这种方式过滤，并不是如同spark一样按照行读取csv，就是一个字符串
//  这是一个失败的案例
public class CreateTable {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //  下面方式并不能实现读取hdfs并删除数据
        DataStreamSource<String> hdfsdata = env.readTextFile("hdfs://192.168.1.10:9000/HadoopFileS/DataSet/MLdataset/seeds_dataset.csv");
        SingleOutputStreamOperator<String> hdfs = hdfsdata.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return value.split(",")[0] != "ID";
            }
        });
        hdfs.writeToSocket("192.168.1.10",7777,new SimpleStringSchema());
        env.execute();

    }
}
