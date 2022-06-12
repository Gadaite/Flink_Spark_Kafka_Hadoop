package WorkAoutSpark.Main20220613;

import org.apache.spark.api.java.function.Function;

import java.io.Serializable;


public class TransToBean implements Function<CityBoundaryModel, ChinaCityBean>, Serializable {
    @Override
    public ChinaCityBean call(CityBoundaryModel v1) throws Exception {
        String center = v1.getCenter().toString();
        String centroid = v1.getCentroid().toString();
        String geometry = v1.getGeometry().toString();
        return new ChinaCityBean(v1.getAdcode(),v1.getName(),center,centroid,
                v1.getChildrenNum(),v1.getLevel(),v1.getParentCenter(),v1.getSubFeatureIndex(),v1.getAcroutes(),geometry);
    }
}
