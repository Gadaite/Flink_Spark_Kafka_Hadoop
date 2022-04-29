package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;

public class SelectScanRowkeyHbaseData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Scan scanrowkey = new Scan();
        //  str$ 末尾匹配，相当于sql中的 %str ^str开头匹配，相当于sql中的str%
        //  LESS <
        //  LESS_OR_EQUAL <=
        //  EQUAL =
        //  NOT_EQUAL <>
        //  GREATER_OR_EQUAL >=
        //  GREATER >
        //  NO_OP
        RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator("2$"));
        scanrowkey.setFilter(rowFilter);
        ResultScanner scanner = gadaite.getScanner(scanrowkey);
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
