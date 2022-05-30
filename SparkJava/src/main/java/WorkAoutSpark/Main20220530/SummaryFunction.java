package WorkAoutSpark.Main20220530;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import scala.Tuple2;
import scala.collection.generic.Sorted;

import java.util.ArrayList;
import java.util.List;

public class SummaryFunction implements Function<Tuple2<String, Iterable<Row>>, List<Row>> {
    @Override
    public List<Row> call(Tuple2<String, Iterable<Row>> v1) throws Exception {
        List<Row> rows = new ArrayList<>();
        String vendor_id = v1._1;
        v1._2.forEach(x ->{
//            rows.add(RowFactory.create(x.get))
        });
        return null;
    }
}
