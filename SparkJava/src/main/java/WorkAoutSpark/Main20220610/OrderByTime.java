package WorkAoutSpark.Main20220610;

import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderByTime implements Function<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>,
        Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>> {
    @Override
    public Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> call(Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> v1) throws Exception {
        List<BrightkiteTotalcheckins> list = new ArrayList<>();
        v1._2.forEach(x ->{
            list.add(x);
        });
        list.sort(Comparator.comparing(BrightkiteTotalcheckins::getTime));
        return new Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>(v1._1,list);
    }
}
