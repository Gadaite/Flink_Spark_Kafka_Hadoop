package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;

//  通过put的api向Hbase中写入数据
public class UpsertHbaseData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        ArrayList puts = new ArrayList();
        Put put1 = new Put(Bytes.toBytes("101"));// 行键位
        put1.addColumn(Bytes.toBytes("F1"), Bytes.toBytes("F11"), Bytes.toBytes("T1:T11"));//   列簇---字段(不需要再指定列簇)---值
        put1.addColumn(Bytes.toBytes("F1"), Bytes.toBytes("F12"), Bytes.toBytes("T1:T22"));
        put1.addColumn(Bytes.toBytes("F2"), Bytes.toBytes("F21"), Bytes.toBytes("T2:T21"));
        put1.addColumn(Bytes.toBytes("F2"), Bytes.toBytes("F22"), Bytes.toBytes("T2:T22"));
        puts.add(put1);
        gadaite.put(put1);
        gadaite.close();
        conn.close();
    }
}
