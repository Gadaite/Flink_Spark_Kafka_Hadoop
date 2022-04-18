package Others;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Get get = new Get(Bytes.toBytes("003"));
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Result result = gadaite.get(get);
        System.out.println(result);

    }
}
