package WorkAoutSpark.Main20220529;

import GadaiteToolGeo.GeoCoordinate;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;

import java.util.HashMap;

/**
 * made by Gadaite
 * 将经纬度的表转换为HashMap结构，坐标点使用自定义的GeoCoordinate的JavaBean类型
 */
public class StationHashMap implements Function<Row, HashMap<Integer, GeoCoordinate>> {
    @Override
    public HashMap<Integer, GeoCoordinate> call(Row v1) throws Exception {
        HashMap<Integer, GeoCoordinate> Map = new HashMap<>();
        int code = v1.getInt(v1.fieldIndex("code"));
        double latitude = v1.getDouble(v1.fieldIndex("latitude"));
        double longitude = v1.getDouble(v1.fieldIndex("longitude"));
        GeoCoordinate geoCoordinate = new GeoCoordinate(longitude, latitude);
        Map.put(code,geoCoordinate);
        return Map;
    }
}
