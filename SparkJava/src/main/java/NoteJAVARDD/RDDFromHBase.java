package NoteJAVARDD;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.spark.rdd.NewHadoopRDD;
import org.apache.spark.sql.SparkSession;

public class RDDFromHBase {
    public static void main(String[] args) {
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("ReadHBase", "ERROR");
        Configuration HBaseConf = HBaseConfiguration.create();
        HBaseConf.set("hbase.zookeeper.quorum","192.168.1.10:2181");
        HBaseConf.set(TableInputFormat.INPUT_TABLE,"Gadaite");
        NewHadoopRDD<ImmutableBytesWritable, Result> HBaseRDD =
                new NewHadoopRDD<>(spark.sparkContext(), TableInputFormat.class, ImmutableBytesWritable.class, Result.class, HBaseConf);
        HBaseRDD.toJavaRDD().foreach(x -> {
            Cell[] cells = x._2.rawCells();
            for (Cell cell:cells){
                System.out.println(cell);
            }
        });
    }
}
