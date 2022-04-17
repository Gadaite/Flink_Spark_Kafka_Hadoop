package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class SelectScanColumnHbaseData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Scan columnscan = new Scan();
        //  这里如果为没有满足筛选条件的值的时候会将空值所在的数据进行返回
        SingleColumnValueFilter columnfilter = new SingleColumnValueFilter(Bytes.toBytes("F1"), Bytes.toBytes("F12"),
                CompareOperator.EQUAL,Bytes.toBytes("G1.G12"));
        columnscan.setFilter(columnfilter);
        ResultScanner scanner = gadaite.getScanner(columnscan);
        for (Result sc:scanner){
            for (Cell c:sc.rawCells()){
                System.out.println(c);
                System.out.println(new String(CellUtil.cloneValue(c)));
            }
        }
    }
}
