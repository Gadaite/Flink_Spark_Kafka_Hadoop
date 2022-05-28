package GadaiteToolGeo;

import java.util.List;

/**
 * made by Gadaite
 * 判断点是否在区域内
 */
public class GeoIFPointInRegion {
    private GeoRegion geoRegion;
    private GeoCoordinate geoCoordinate;

    public GeoRegion getGeoRegion() {
        return geoRegion;
    }

    public void setGeoRegion(GeoRegion geoRegion) {
        this.geoRegion = geoRegion;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }

    public GeoIFPointInRegion() {}

    public GeoIFPointInRegion(GeoRegion geoRegion, GeoCoordinate geoCoordinate) {
        this.geoRegion = geoRegion;
        this.geoCoordinate = geoCoordinate;
    }

    /**
     * 判断坐标点是否在区域内
     * 返回布尔值
     */
    public boolean PointInRegion(GeoCoordinate geoCoordinate,GeoRegion geoRegion){
        List<GeoCoordinate> points = geoRegion.getPoints();
        /**
         * 将第一个点加到最后一个点上
         */
        points.add(points.get(0));
        int size = points.size();
        boolean boundOrVertex = false;
        double precision = 2e-10;//浮点数类型比较的容差
        /**
         * 如果就是区域的顶点，直接判为是
         */
        for (int i=0;i < size -2;i++){
            if (geoCoordinate.getLat() == points.get(i).getLat() && geoCoordinate.getLon() == points.get(i).getLon()){
                return true;
            }
        }
        /**
         * 如果不是区域的顶点
         */
        for (int i=0;i < size -1;i++){
            /**
             * 前后两个坐标点
             */
            GeoCoordinate geoCoordinate1 = points.get(i);
            GeoCoordinate geoCoordinate2 = points.get(i + 1);

        }
        return false;
    }

}
