package Hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
public class Countlines {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        FileSystem fs=FileSystem.get(
                new URI( "hdfs://192.168.1.10:9000"), new Configuration(), "root");
        FSDataInputStream in = fs.open(
                new Path( "hdfs://192.168.1.10:9000/HadoopFileS/DataSet/MLdataset/College.csv"));
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        long count = 0;
        String line;
        while ((line = d.readLine()) != null) {
            count += 1L;
        }
        System.out.println( count );
        d.close();
        in.close();
        fs.close();
    }
}
