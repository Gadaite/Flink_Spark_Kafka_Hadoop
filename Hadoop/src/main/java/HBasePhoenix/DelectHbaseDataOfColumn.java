package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class DelectHbaseDataOfColumn {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Delete delete = new Delete(Bytes.toBytes("003"));
        //  删除某个列簇下指定的某个字段时，使用addColum，并传递两个参数
        Delete delete1 = delete.addColumn(Bytes.toBytes("F2"), Bytes.toBytes("F21"));
        gadaite.delete(delete1);
        gadaite.close();
        conn.close();
    }
}
