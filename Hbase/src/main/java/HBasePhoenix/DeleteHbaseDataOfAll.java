package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/***
 * 删除Hbase中某一条数据
 */
public class DeleteHbaseDataOfAll {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Delete delete = new Delete(Bytes.toBytes("101"));
        gadaite.delete(delete);
        gadaite.close();
        conn.close();
    }
}
