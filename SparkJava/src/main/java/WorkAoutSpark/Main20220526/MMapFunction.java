package WorkAoutSpark.Main20220526;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MMapFunction implements Function<Tuple2<String, Iterable<Row>>, HashMap<String, List<Row>>> {
    @Override
    public HashMap<String, List<Row>> call(Tuple2<String, Iterable<Row>> v1) throws Exception {
        HashMap<String, List<Row>> map = new HashMap<>();
        List<Row> rows = new ArrayList<>();
        String GroupField = v1._1;
        v1._2().forEach(x ->{
            rows.add(x);
        });
        map.put(GroupField,rows);
        return map;
    }
}
