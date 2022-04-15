package TableApi;


import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.ConnectorDescriptor;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.hadoop.conf.Configuration;

import java.util.Map;

/**
 * tableEnv.connect(new ExternalSystemXYZ().version("0.11"))
 *    .withFormat(new Json().jsonSchema("{...}").failOnMissingField(false))
 *    .withSchema(new Schema().field("user-name", "VARCHAR").from("u_name").field("count", "DECIMAL")
 *    .registerSource("MyTable");
 * **/
public class HdfsTable {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tenv = StreamTableEnvironment.create(env);
//        tenv.connect(new Kafka().topic("flinkapp").startFromEarliest()).withSchema();
    }
}
