package Hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.*;
import java.io.IOException;
import java.net.URI;
//import org.apache.hadoop.fs.
public class CatINFO {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception{
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.1.10:9000"),
                configuration,"root");
    }
    @Test
    public void catinfo() throws Exception{
        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path("/"),true);
        while (iterator.hasNext()){
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println("文件路径是:"+fileStatus.getLen());
            System.out.println("副本数为:"+fileStatus.getBlockLocations());
            System.out.println("bloc为:"+fileStatus.getReplication());
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
