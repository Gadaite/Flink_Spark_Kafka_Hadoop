package WorkAoutSpark.Main20220607;

import org.apache.spark.sql.api.java.UDF1;

public class UDFFunction implements UDF1<Integer,Integer> {
    @Override
    public Integer call(Integer integer) throws Exception {
        return 2*integer;
    }
}
