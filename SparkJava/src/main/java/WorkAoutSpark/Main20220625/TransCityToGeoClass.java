package WorkAoutSpark.Main20220625;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;

public class TransCityToGeoClass implements Function<Row,ChinaCityBoundary> {
    @Override
    public ChinaCityBoundary call(Row v1) throws Exception {
        int adcode = v1.getInt(v1.fieldIndex("adcode"));
        int childrenNum = v1.getInt(v1.fieldIndex("childrenNum"));
        int parentCenter = v1.getInt(v1.fieldIndex("parentCenter"));
        int subFeatureIndex = v1.getInt(v1.fieldIndex("subFeatureIndex"));
        int acroutes = v1.getInt(v1.fieldIndex("acroutes"));
        String level = v1.getString(v1.fieldIndex("level"));
        String name = v1.getString(v1.fieldIndex("name"));
        String center = v1.getString(v1.fieldIndex("center"));
        String centroid = v1.getString(v1.fieldIndex("centroid"));
        String geometry = v1.getString(v1.fieldIndex("region"));
        Geometry readcenter = new WKBReader().read(WKBReader.hexToBytes(center));
        Geometry readcentroid = new WKBReader().read(WKBReader.hexToBytes(centroid));
        Geometry readgeometry = new WKBReader().read(WKBReader.hexToBytes(geometry));
        return new ChinaCityBoundary(adcode,name,readcenter,readcentroid,childrenNum,level,parentCenter,subFeatureIndex,acroutes,readgeometry);
    }
}
