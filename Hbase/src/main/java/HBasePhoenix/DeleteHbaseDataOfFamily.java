package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class DeleteHbaseDataOfFamily {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        //  new Delete(Bytes.toBytes("这里填写行键是直接删除该行键对应的所有记录"));
        Delete delete = new Delete(Bytes.toBytes("001"));
        Delete delete1 = delete.addFamily(Bytes.toBytes("F1"));
        gadaite.delete(delete1);
        gadaite.close();
        conn.close();

    }
}
