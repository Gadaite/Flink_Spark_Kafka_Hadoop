package SinkApi;

import Beans.SensorReading;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectJDBC {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //  准备数据源
        DataStreamSource<String> dataStreamSource = env.readTextFile("F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt");
        //  转换数据源类型
        SingleOutputStreamOperator<SensorReading> sensordata = dataStreamSource.map(line -> {
            String[] strings = line.split(",");
            return new SensorReading(strings[0], new Long(strings[1]), new Double(strings[2]));
        });
        sensordata.addSink(new MYSQLJDBC());
        env.execute();
    }
    public static class MYSQLJDBC extends RichSinkFunction<SensorReading> {
        //  做数据库连接，不必要反复连接，使用富函数
        Connection con = null;
        //  预编译处理
        PreparedStatement insertStmt = null;
        PreparedStatement updateStmt = null;
        @Override
        public void open(Configuration parameters) throws Exception {
            //  结合Flink，先进行预编译初始化
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3306/Gadaite","root","LYP809834049");
            insertStmt = con.prepareStatement("insert into SensorTable (id,temp) value (?,?)");
            updateStmt = con.prepareStatement("update SensorTable set temp = ? where id = ?");
        }
        @Override
        public void invoke(SensorReading value, Context context) throws Exception {
            //  数据来一条便执行一次，实时入库
            //  没有更新就插入
            updateStmt.setDouble(1,value.getTemperature());
            updateStmt.setString(2,value.getId());
            updateStmt.execute();
            if(updateStmt.getUpdateCount() == 0){
                insertStmt.setString(1,value.getId());
                insertStmt.setDouble(2,value.getTemperature());
                insertStmt.execute();
            }
        }
        @Override
        public void close() throws Exception {
            insertStmt.close();
            updateStmt.close();
            con.close();    //  关闭数据库连接
        }
    }
}
