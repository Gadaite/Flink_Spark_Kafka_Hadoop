package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
//  列名前缀过滤器
public class SelectColumnPrefixFilter {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Scan scan = new Scan();
        //  值返回指定列簇的数据
        ColumnPrefixFilter f2 = new ColumnPrefixFilter(Bytes.toBytes("F2"));
        scan.setFilter(f2);
        ResultScanner scanner = gadaite.getScanner(scan);
        for (Result sc:scanner){
            for (Cell c:sc.rawCells()){
                System.out.println(c);
                System.out.println(new String(CellUtil.cloneValue(c)));
            }
        }

    }
}
