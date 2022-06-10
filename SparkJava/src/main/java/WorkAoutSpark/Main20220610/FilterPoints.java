package WorkAoutSpark.Main20220610;

import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

public class FilterPoints implements Function<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>, Boolean> {
    private Integer count;
    private Boolean b;

    @Override
    public Boolean call(Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> v1) throws Exception {
            count = 0;
            v1._2.forEach(x -> {
                count++;
            });
            return count >= 2;
    }
}
