package GadaiteToolGeo;

import jodd.util.StringUtil;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

/**
 * made by Gadaite
 * 2022-06-11
 * 字符串的LineString解析为LineString
 */
public class StrLineSTRING {
    private String str;

    public StrLineSTRING(String str) {
        this.str = str;
    }
    public StrLineSTRING(){}
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    /**
     * made by Gadaite
     * 字符串的LineString转换为JTS的LineString
     * @param str 待解析字符串
     * @return
     */
    public LineString FormatToLineString(String str){
        String data = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        String[] points = data.split(",");
        List<Coordinate> coordinates = new ArrayList<>();
        for (String point : points){
            String[] lon_lat = point.split(" ");
            List<String> list = new ArrayList<>();
            for (String s : lon_lat){
                if (!StringUtil.isBlank(s)){
                    list.add(s);
                }
            }
            coordinates.add(new Coordinate(Double.parseDouble(list.get(0)), Double.parseDouble(list.get(1))));
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString lineString = geometryFactory.createLineString(coordinates.toArray(new Coordinate[0]));
        return lineString;
    }

    @Override
    public String toString() {
        return "StrLineSTRING{" +
                "str='" + str + '\'' +
                '}';
    }
}
