package Hadoop;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.Configuration;
import org.junit.*;
import java.io.*;
import java.net.URI;
public class Mkdir {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception{
        Configuration configuration = new Configuration();
        fileSystem= FileSystem.get(new URI("hdfs://192.168.1.10:9000"),configuration,"root");
    }
    @Test
    public void mkdir(){
        try{
            fileSystem.mkdirs(new Path("/Ideatest"));
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
