package WorkAoutSpark.Main20220530;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;

public class GroupFunction implements Function<Row,String> {
    @Override
    public String call(Row v1) throws Exception {
        return v1.getString(v1.fieldIndex("vendor_id"));
    }
}
