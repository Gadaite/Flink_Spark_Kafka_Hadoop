package GadaiteToolGeo;


import java.util.ArrayList;
import java.util.List;

/**
 * made by Gadaite
 * 定义区域模型
 * 用于定义一个封闭的区域(一连串1有序的GeoCoordinate类型的坐标点构成)
 */
public class GeoRegion {
    List<GeoCoordinate> points = new ArrayList<>();

    public List<GeoCoordinate> getPoints() {
        return points;
    }

    public void setPoints(List<GeoCoordinate> points) {
        this.points = points;
    }
    public GeoRegion(){}
    public GeoRegion(List<GeoCoordinate> points) {
        this.points = points;
    }

}
