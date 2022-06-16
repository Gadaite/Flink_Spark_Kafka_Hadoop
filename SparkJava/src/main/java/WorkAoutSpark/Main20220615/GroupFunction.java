package WorkAoutSpark.Main20220615;

import org.apache.spark.api.java.function.Function;

import java.io.Serializable;

/**
 * 按照天数进行分组
 */
public class GroupFunction implements Function<Trajectlonlat, String>, Serializable {
    @Override
    public String call(Trajectlonlat v1) throws Exception {
        String day = v1.getDatetime().toString().split(" ")[0];
        return day;
    }
}
