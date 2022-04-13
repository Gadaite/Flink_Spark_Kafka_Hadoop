package Hadoop;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.Configuration;
import org.junit.*;
import java.io.*;
import java.net.URI;
public class Upload_File {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception {
        Configuration configuration =new Configuration();
        fileSystem =FileSystem.get(new URI("hdfs://192.168.1.10:9000"),configuration,"root");
    }
    //上传一个文件
    @Test
    public void upload(){
        try {
            fileSystem.copyFromLocalFile(new Path("F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt"),new Path("/sensor.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public void close(){
        try {
            fileSystem.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
