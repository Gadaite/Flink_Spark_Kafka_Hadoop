package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class CreateHbaseTable {
    public static void main(String[] args) throws Exception{
        Configuration conf = HBaseConfiguration.create();
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.1.10:2181");
        Connection con = ConnectionFactory.createConnection(conf);
        Admin admin = con.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("ideatest".getBytes()));
        HColumnDescriptor f1 = new HColumnDescriptor("F1");
        HColumnDescriptor f2 = new HColumnDescriptor("F2");
        f2.setVersions(1, 3);
        hTableDescriptor.addFamily(f1).addFamily(f2);
        admin.createTable(hTableDescriptor);
        admin.close();
        con.close();
    }
}
