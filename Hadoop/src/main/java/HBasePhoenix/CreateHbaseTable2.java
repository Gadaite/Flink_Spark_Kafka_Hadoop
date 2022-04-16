package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;

public class CreateHbaseTable2 {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        if(!admin.tableExists(TableName.valueOf("IDEATEST"))){
            TableName IDEATEST = TableName.valueOf("IDEATEST");
            //  表描述构造器
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(IDEATEST);
            //  列族描述器构造器
            ColumnFamilyDescriptorBuilder col1 = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("col1"));
            ColumnFamilyDescriptorBuilder col2 = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("col2"));
            //  列描述器
            ColumnFamilyDescriptor build1 = col1.build();
            ColumnFamilyDescriptor build2 = col2.build();
            //  添加列簇
            TableDescriptorBuilder builder = tableDescriptorBuilder.setColumnFamily(build1).setColumnFamily(build2);
            //  表描述器
            TableDescriptor tableDescriptor = builder.build();
            //  创建表
            admin.createTable(tableDescriptor);
        }
        else {
            System.out.println("表已存在");
        }
        admin.close();
        conn.close();
    }
}
