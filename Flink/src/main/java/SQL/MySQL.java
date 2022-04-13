package SQL;

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class MySQL {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);
        RowTypeInfo rowTypeInfo =new RowTypeInfo(BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.DOUBLE_TYPE_INFO) ;
        new JDBCInputFormat();
        DataStreamSource<Row> mysqldata = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.cj.jdbc.Driver")
                .setDBUrl("jdbc:mysql://192.168.1.10/Gadaite")
                .setUsername("root")
                .setPassword("LYP809834049")
                .setQuery("select * from SensorTable")
                .setRowTypeInfo(rowTypeInfo)
                .finish()
        );
        tableEnvironment.createTemporaryView("tabletest",mysqldata);
        Table res = tableEnvironment.sqlQuery("select * from tabletest");
        tableEnvironment.toAppendStream(res,Row.class).print();
        env.execute();
    }
}
