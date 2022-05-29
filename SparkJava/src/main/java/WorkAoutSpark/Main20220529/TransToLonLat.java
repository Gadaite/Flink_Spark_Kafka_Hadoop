package WorkAoutSpark.Main20220529;

import GadaiteToolGeo.GeoCoordinate;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * made by Gadaite
 * 保留原有的ROW结构同时将坐标编号更改为支付串的经纬度
 */
public class TransToLonLat implements Function<Row, Row> {
    private List<HashMap<Integer, GeoCoordinate>> collect;

    public TransToLonLat(List<HashMap<Integer, GeoCoordinate>> collect) {
        this.collect = collect;
    }

    @Override
    public Row call(Row v1) throws Exception {
        int column1 = v1.getInt(v1.fieldIndex("Column1"));
        String start_date = v1.getString(v1.fieldIndex("start_date"));
        int start_station_code = v1.getInt(v1.fieldIndex("start_station_code"));
        String end_date = v1.getString(v1.fieldIndex("end_date"));
        int end_station_code = v1.getInt(v1.fieldIndex("end_station_code"));
        int duration_sec = v1.getInt(v1.fieldIndex("duration_sec"));
        int is_member = v1.getInt(v1.fieldIndex("is_member"));
        String start_station = "";
        String end_station = "";
        int count = 0;
        for (HashMap<Integer, GeoCoordinate> hashMap : collect){
            for (Map.Entry<Integer, GeoCoordinate> entries : hashMap.entrySet()){
                if (entries.getKey() == start_station_code){
                    start_station = GetLonLat(hashMap.get(start_station_code));
                    count = count + 1;
                }
                if (entries.getKey() == end_station_code){
                    end_station = GetLonLat(hashMap.get(end_station_code));
                    count = count + 1;
                }
                if (count == 2){
                    break;
                }
            }
        }
        Row row = RowFactory.create(column1, start_date, start_station, end_date, end_station, duration_sec, is_member);
        return row;
    }

    /**
     * 已封装类型提取经纬度坐标信息，用逗号拼接经纬度
     * @param geoCoordinate
     * @return
     */
    private String GetLonLat(GeoCoordinate geoCoordinate){
        String lon = String.valueOf(geoCoordinate.getLon());
        String lat = String.valueOf(geoCoordinate.getLat());
        return lon + "," + lat;
    }
}
