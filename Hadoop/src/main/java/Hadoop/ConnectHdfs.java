package Hadoop;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class ConnectHdfs {
    public static void main(String[] args) {
        try {
            // 配置连接地址
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://192.168.1.10:9000");
            FileSystem fs = FileSystem.get(conf);

            // 打开文件并读取输出
            Path test = new Path("/HadoopFileS/DataSet/Others/bank_train.csv");
            FSDataInputStream ins = fs.open(test);
            int ch = ins.read();
            System.out.println(ch);
            while (ch != -1) {
                System.out.print((char)ch);
                ch = ins.read();
            }
            System.out.println();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
