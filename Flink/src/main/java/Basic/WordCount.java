package Basic;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCount {
    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        String path = "F:\\CodeG50\\BiGData\\Spark\\src\\main\\resources\\DataSets\\hello.txt";
        DataSource<String> inputdata = env.readTextFile(path);
        //  按照空格进行分词
        DataSet<Tuple2<String,Integer>> result = inputdata.flatMap(new MyFlatMapper()).groupBy(0).sum(1);
        // 输出
        result.print();
    }
    //  自定义mapfunction接口,映射成一个二元组
    public static class MyFlatMapper implements FlatMapFunction<String, Tuple2<String,Integer>>{
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] words = value.split(" ");
            for(String word:words){
                out.collect(new Tuple2<String, Integer>(word,1));
            }

        }
    }

}
