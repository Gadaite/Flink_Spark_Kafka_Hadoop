package WorkAoutSpark.Main20220608;

import GadaiteToolGeo.LonLatToGeoHash;
import org.apache.spark.api.java.function.Function;

public class GetGeoHash implements Function<BrightkiteTotalcheckins,BrightkiteTotalcheckins> {
    public Integer precise;

    public Integer getPrecise() {
        return precise;
    }

    public void setPrecise(Integer precise) {
        this.precise = precise;
    }

    public GetGeoHash(Integer precise) {
        this.precise = precise;
    }

    @Override
    public BrightkiteTotalcheckins call(BrightkiteTotalcheckins v1) throws Exception {
        Double latitude = v1.getLatitude();
        Double longitude = v1.getLongitude();
        LonLatToGeoHash lonLatToGeoHash = new LonLatToGeoHash();
        String geoHash = lonLatToGeoHash.getGeoHash(longitude, latitude, precise);
        v1.setLocation(geoHash);
        return v1;
    }
}
