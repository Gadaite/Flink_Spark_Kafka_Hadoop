package HBasePhoenix;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

public class JavaConectHbase {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.1.10:2181");
        try {
            Connection connection = ConnectionFactory.createConnection(conf);
//            Admin admin = connection.getAdmin();
            System.out.println("连接测试通过");
        } catch (Exception e){
            e.printStackTrace();
        }
        Connection conn = ConnectionFactory.createConnection(conf);
        Table gadaite = conn.getTable(TableName.valueOf("Gadaite"));
        ResultScanner scanner = gadaite.getScanner(new Scan());
        for (Result sc:scanner){
            for (Cell c:sc.rawCells()){
                System.out.println(c);
            }
        }
    }
}

