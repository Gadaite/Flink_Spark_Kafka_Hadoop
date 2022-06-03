package WorkAoutSpark.Main20220602;

import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;

public class SumFunction implements Function2<Audi,Audi,Audi> {
    @Override
    public Audi call(Audi v1, Audi v2) throws Exception {
        Audi audi = new Audi();
        int Allprice = v1.getPrice() + v2.getPrice();
        audi.setPrice(Allprice);
        audi.setModel(v1.getModel());
        audi.setYear(v1.getYear());
        return audi;
    }
}
