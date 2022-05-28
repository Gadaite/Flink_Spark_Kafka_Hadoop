package GadaiteToolGeo;

/**
 * made by Gadaite
 * 定义线段模型
 */
public class GeoLineSegment {
    /**
     * 两个点模型构成
     */
    private GeoCoordinate geoCoordinate1;
    private GeoCoordinate geoCoordinate2;

    public GeoCoordinate getGeoCoordinate1() {
        return geoCoordinate1;
    }

    public void setGeoCoordinate1(GeoCoordinate geoCoordinate1) {
        this.geoCoordinate1 = geoCoordinate1;
    }

    public GeoCoordinate getGeoCoordinate2() {
        return geoCoordinate2;
    }

    public void setGeoCoordinate2(GeoCoordinate geoCoordinate2) {
        this.geoCoordinate2 = geoCoordinate2;
    }

    public GeoLineSegment(){}
    public GeoLineSegment(GeoCoordinate geoCoordinate1, GeoCoordinate geoCoordinate2) {
        this.geoCoordinate1 = geoCoordinate1;
        this.geoCoordinate2 = geoCoordinate2;
    }

    /**
     * 返回一个直线模型
     * @return
     */
    public GeoLineStraight ConvertToStraightLineModel(){

        double point_lon1 = geoCoordinate1.getLon();
        double point_lat1 = geoCoordinate1.getLat();
        double point_lon2 = geoCoordinate2.getLon();
        double point_lat2 = geoCoordinate2.getLat();

        if (point_lon1 != point_lon2 ){
            /**
             * 斜率和截距
             * 斜率存在时
             */
            double slope = (point_lat1 - point_lat2) / (point_lon1 - point_lon2);
            double intercept = point_lat1 - (slope * point_lon1);
            return new GeoLineStraight(slope,intercept,-9999);
        }else {
            /**
             * 斜率不存在时
             */
            return new GeoLineStraight(-9999,-9999,point_lon1);
        }

    }
}
