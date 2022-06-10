package WorkAoutSpark.Main20220610;

import GadaiteToolGeo.LonLatToGeoHash;
import org.apache.spark.api.java.function.Function;

public class TransGeo implements Function<BrightkiteTotalcheckins, BrightkiteTotalcheckins> {
    @Override
    public BrightkiteTotalcheckins call(BrightkiteTotalcheckins v1) throws Exception {
            v1.setLocation(new LonLatToGeoHash().getGeoHash(v1.getLongitude(), v1.getLatitude(), 9));
            return v1;
    }
}
