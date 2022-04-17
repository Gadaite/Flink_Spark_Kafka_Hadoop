package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class SelectGetHbaseData {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        Result result = gadaite.get(new Get(Bytes.toBytes("002")));
        Cell[] cells = result.rawCells();
        for(Cell cell:cells){
            System.out.println("INFO:   "+cell);
            System.out.println(new String(CellUtil.cloneRow(cell)));    //  行键
            System.out.println(new String(CellUtil.cloneFamily(cell)));     //  列簇
            System.out.println(new String(CellUtil.cloneQualifier(cell)));      //  字段
            System.out.println(new String(CellUtil.cloneValue(cell)));      //  值
            System.out.println(cell.getTimestamp());    //  时间戳
        }
        gadaite.close();
        conn.close();
    }
}
