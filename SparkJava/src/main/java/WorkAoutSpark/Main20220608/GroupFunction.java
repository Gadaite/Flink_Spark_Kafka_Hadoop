package WorkAoutSpark.Main20220608;

import org.apache.spark.api.java.function.Function;

public class GroupFunction implements Function<BrightkiteTotalcheckins,Integer> {
    @Override
    public Integer call(BrightkiteTotalcheckins v1) throws Exception {
        return v1.getUser();
    }
}
