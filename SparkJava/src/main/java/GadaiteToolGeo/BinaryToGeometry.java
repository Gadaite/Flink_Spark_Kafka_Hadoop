package GadaiteToolGeo;

import org.geolatte.geom.ByteBuffer;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.codec.Wkb;
import org.locationtech.jts.geom.LineString;

/**
 * made by Gadaite
 * 2022-06-11
 * 使用org.geolatte.geom.Geometry，间接转换从数据库中读取到的二进制文件
 */
public class BinaryToGeometry {
    private String str;

    public BinaryToGeometry(String str) {
        this.str = str;
    }
    public BinaryToGeometry(){}

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    /**
     * made by Gadaite
     * @param str 从postgresql等读取下来带有[]的二进制数据
     * @return  org.geolatte.geom.Geometry
     */
    public Geometry GeolatteGeometry(String str){
        String sss = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
        Geometry<?> geometry = Wkb.fromWkb(ByteBuffer.from(sss));
        return geometry;
    }

    /**
     * made by Gadaite
     * @param str 从postgresql等读取下来带有[]的二进制数据
     * @return  org.locationtech.jts.geom.LineString
     */
    public LineString JTSLineString(String str){
        Geometry geometryA = GeolatteGeometry(str);
        String s = geometryA.toString();
        LineString lineString = new StrLineSTRING().FormatToLineString(s);
        return lineString;
    }
    @Override
    public String toString() {
        return "BinaryToGeometry{" +
                "str='" + str + '\'' +
                '}';
    }
}
