package WorkAoutSpark.Main20220610;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;

public class ErrorToStringRow implements Function<Brightkite2008, Row> {
    @Override
    public Row call(Brightkite2008 v1) throws Exception {
        Row row = RowFactory.create(v1.getUser(), v1.getStartTime(), v1.getEndTime(),
                String.valueOf(v1.getTrajectory()));
        return row;
    }
}
