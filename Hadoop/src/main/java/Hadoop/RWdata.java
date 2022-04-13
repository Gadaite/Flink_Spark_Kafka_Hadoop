package Hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.junit.*;
import java.io.*;
import java.net.URI;

public class RWdata {
    FileSystem fileSystem = null;
    @Before
    public void init() throws Exception {
        Configuration configuration =new Configuration();
            fileSystem =FileSystem.get(new URI("hdfs://192.168.1.5:9000"),configuration,"root");
    }
    @Test
    public void readWrite() throws Exception {
        FSDataInputStream open =fileSystem.open(new Path("/Hadoopfiles/kmeans_data.txt"));
        FSDataOutputStream create = fileSystem.create(new Path("/IdeaTest/"));
        //读取数据
        BufferedReader br =new BufferedReader(new InputStreamReader(open));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(create));
        //循环读取数据
        String line = null;
        //行数
        while ((line =br.readLine())!= line){
            System.out.println("Enter this path");
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
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
