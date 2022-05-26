package CETC10S.Main20220526;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;


public class GroupFunction implements Function<Row,String> {
    @Override
    public String call(Row v1) throws Exception {
        String model = v1.getString(v1.fieldIndex("model"));
        String year = String.valueOf(v1.getInt(v1.fieldIndex("year")));
        return model + "-" + year;
    }
}
