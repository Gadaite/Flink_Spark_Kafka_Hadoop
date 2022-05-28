package GadaiteToolGeo;

/**
 * made by Gadaite
 * 定义坐标模型
 * 定义坐标点的类(经度，纬度)
 */
public class GeoCoordinate {
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public GeoCoordinate(){}
    public GeoCoordinate(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
