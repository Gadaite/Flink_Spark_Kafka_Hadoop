package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;

public class ScanFilterHbaseData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        //  全表扫描
        Scan scanall = new Scan();
        ResultScanner scannerall = gadaite.getScanner(scanall);
        for ( Result sc:scannerall){
            for (Cell ce:sc.rawCells()){
                //  ce:sc的对象，对应一条数据
                String rowkey = new String(CellUtil.cloneRow(ce));//行键
                String column = new String(CellUtil.cloneFamily(ce));//列簇
                String field = new String(CellUtil.cloneQualifier(ce));//字段
                String values = new String(CellUtil.cloneValue(ce));//值
                System.out.println(rowkey+" "+column+" "+field+" "+values);
            }
        }
    }
}
