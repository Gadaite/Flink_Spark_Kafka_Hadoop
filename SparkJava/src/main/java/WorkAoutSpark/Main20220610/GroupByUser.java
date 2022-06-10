package WorkAoutSpark.Main20220610;

import org.apache.spark.api.java.function.Function;

public class GroupByUser implements Function<BrightkiteTotalcheckins, Integer> {
    @Override
    public Integer call(BrightkiteTotalcheckins v1) throws Exception {
            return v1.getUser();
    }
}
