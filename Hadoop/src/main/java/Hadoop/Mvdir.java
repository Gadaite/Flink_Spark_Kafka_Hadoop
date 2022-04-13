package Hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.*;
import java.net.URI;
import java.io.*;
public class Mvdir {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception{
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.1.5:9000"),configuration,"root");
    }
    @Test
    public void mvdir(){
        try{
            fileSystem.delete(new Path("/Ideatest"));
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
}
