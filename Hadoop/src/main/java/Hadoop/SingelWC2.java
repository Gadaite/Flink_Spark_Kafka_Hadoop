//package GadaiteGroupID.Hadoop;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.*;
//
//public class SingelWC2 {
//    public static void main(String[] args) throws Exception {
//        HashMap<String,Integer> map = new HashMap<String,Integer>();
//        //获取连接
//        Configuration conf = new Configuration();
//        FileSystem fileSystem = FileSystem.get(conf);
//        //读取数据
//        String inputfilepath = "F:\\CodeG50\\sparkAll\\src\\main\\scala\\GadaiteGroupID\\DataSets\\Vegetable-carrots.txt";
//        FSDataInputStream open = fileSystem.open(new Path(inputfilepath));
//        BufferedReader br = new BufferedReader(new InputStreamReader(open));
//        //处理数据 (key,value)
//        String line = null;
//        //一行一行的读取数据
//        while ((line=br.readLine()) != null){
//            String[] splits = line.split(" ");
//            for (String word : splits) {
//                Integer count = map.getOrDefault(word, 0);
//                count++;
//                map.put(word,count);
//            }
//        }
//        //给reduce
//        //给一个写出去的路径
//        String oputputpath = "F:\\CodeG50\\sparkAll\\src\\main\\scala\\GadaiteGroupID\\TestData";
//        FSDataOutputStream out = fileSystem.create(new Path(oputputpath));
//        //遍历map
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        //排序
//        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
//        list.sort(new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                //降序
//                return o2.getValue()-o1.getValue();
//            }
//        });
//        //循环遍历
//        for (Map.Entry<String, Integer> entry : list) {
//            bw.write(entry.getKey()+"="+ entry.getValue()+"\t\n");
//            bw.newLine();
//        }
//        //关流
//        br.close();
//        bw.close();
//    }
//}
