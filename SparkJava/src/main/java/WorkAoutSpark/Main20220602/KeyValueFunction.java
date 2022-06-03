package WorkAoutSpark.Main20220602;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class KeyValueFunction implements PairFunction<Audi,String,Audi> {
    @Override
    public Tuple2<String, Audi> call(Audi audi) throws Exception {
        String model = audi.getModel().split(" ")[1];
        Integer year = audi.getYear();
        return new Tuple2<String, Audi>(model + "_" + String.valueOf(year),audi);
    }
}
