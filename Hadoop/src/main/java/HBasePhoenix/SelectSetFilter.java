package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

//  多条件过滤集合

public class SelectSetFilter {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        FilterList list = new FilterList();
        Scan scan = new Scan();
        RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator("002"));
        ColumnPrefixFilter f2 = new ColumnPrefixFilter(Bytes.toBytes("F2"));
        list.addFilter(rowFilter);
        list.addFilter(f2);
        scan.setFilter(list);
        ResultScanner scanner = gadaite.getScanner(scan);
        for (Result sc:scanner){
            for (Cell c:sc.rawCells()){
                System.out.println(c);
                System.out.println(new String(CellUtil.cloneValue(c)));
            }
        }

    }
}
