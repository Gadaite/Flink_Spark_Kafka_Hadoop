package SchemaMake;


import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.descriptors.StreamTableDescriptor;
import org.apache.flink.types.Row;

public class SoketSchema {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true);
        conf.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        //  DataStreamSource<String> socketdata = env.socketTextStream("192.168.1.10", 7777);
        StreamTableEnvironment tenv = StreamTableEnvironment.create(env);
        //  ID, area, perimeter, compactness, lengthOfKernel, widthOfKernel, asymmetryCoefficient, lengthOfKernelGroove, seedType
        StreamTableDescriptor streamTableDescriptor = tenv.connect(new FileSystem().path("hdfs://192.168.1.10:9000/HadoopFileS/DataSet/MLdataset/seeds_dataset.csv"))
                .withSchema(new Schema().field("ID", DataTypes.STRING()).field("area", DataTypes.STRING())
                        .field("perimeter", DataTypes.STRING()).field("compactness", DataTypes.STRING())
                        .field("lengthOfKernel", DataTypes.STRING()).field("widthOfKernel", DataTypes.STRING())
                        .field("asymmetryCoefficient", DataTypes.STRING())
                        .field("lengthOfKernelGroove", DataTypes.STRING()).field("seedType", DataTypes.STRING())
                );
        streamTableDescriptor.createTemporaryTable("table");
        Table table = tenv.from("table");
        Table res = tenv.sqlQuery("select * from table");
        tenv.toAppendStream(res, Row.class).print();
        env.execute();
    }
}
