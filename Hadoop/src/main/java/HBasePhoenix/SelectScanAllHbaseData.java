package HBasePhoenix;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

public class SelectScanAllHbaseData {
    public static void main(String[] args) throws Exception{
        //  连接信息，连接对象
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();

        //  连接Hbase数据库中的表
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Scan scan = new Scan();
        ResultScanner scanner = gadaite.getScanner(scan);

        //  读取Hbase表中的数据
        for(Result sc:scanner){
            //  sc：对应cell集合，Hbase的一个列簇
            for (Cell c:sc.rawCells()){
                //  c:sc的对象，对应一条数据
                String rowkey = new String(CellUtil.cloneRow(c));//行键
                String column = new String(CellUtil.cloneFamily(c));//列簇
                String field = new String(CellUtil.cloneQualifier(c));//字段
                String values = new String(CellUtil.cloneValue(c));//值
//                String s = new String(CellUtil.copyRow(c));//行键
                System.out.println(rowkey+" "+column+" "+field+" "+values);
//                System.out.println(s);
            }
        }
    }
}
