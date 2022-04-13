package Hadoop;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.junit.*;
//import org.apache.hadoop.
public class Read_data {
    long starttime = System.currentTimeMillis();
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception {
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.1.10:9000"), configuration, "root");
    }
    @Test
    public void readdata(){
        try{
            FSDataInputStream open = fileSystem.open(new Path("/HadoopFileS/DataSet/Others/bank_train.csv"));
            int lines = open.read();
            System.out.println(lines);
            int count =0;
            while (lines != -1){
                System.out.print((char)lines);
                count = count +1;
                lines = open.read();
            }
            System.out.println("Alliter:"+count);
            long endtime = System.currentTimeMillis();
            System.out.println("Alltime:"+(endtime-starttime));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @After
    public void close(){
        try{
            fileSystem.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//    import Thread
//    Thread.sleep(1000000)

}
