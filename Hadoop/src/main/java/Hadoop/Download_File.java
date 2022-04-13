package Hadoop;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.Configuration;
import org.junit.*;
import java.io.*;
import java.net.URI;
public class Download_File {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception{
        Configuration configuration =  new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.1.5:9000"),configuration,"root");
    }
    @Test
    public void download(){
        try{
            fileSystem.copyToLocalFile(new Path("/Hadoopfiles/bank_train.csv"),
                    new Path("F:\\CodeG50\\sparkAll\\src\\main\\scala\\GadaiteGroupID\\TestData\\bank_train.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @After
    public void close(){
        try{
            fileSystem.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
