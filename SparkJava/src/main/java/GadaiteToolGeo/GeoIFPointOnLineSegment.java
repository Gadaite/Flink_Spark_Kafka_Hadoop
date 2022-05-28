package GadaiteToolGeo;

/**
 * made by Gadaite
 * 点是否在线段上
 */
public class GeoIFPointOnLineSegment {
    private GeoCoordinate geoCoordinate;
    private GeoLineSegment geoLineSegment;

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }

    public GeoLineSegment getGeoLineSegment() {
        return geoLineSegment;
    }

    public void setGeoLineSegment(GeoLineSegment geoLineSegment) {
        this.geoLineSegment = geoLineSegment;
    }

    public GeoIFPointOnLineSegment(){}
    public GeoIFPointOnLineSegment(GeoCoordinate geoCoordinate, GeoLineSegment geoLineSegment) {
        this.geoCoordinate = geoCoordinate;
        this.geoLineSegment = geoLineSegment;
    }

    /**
     *
     * @param geoCoordinate 需要判断的点
     * @param geoLineSegment 需要比对的直线
     * @param error 误差范围
     * @return
     */
    public boolean PointOnSegmentLine(GeoCoordinate geoCoordinate, GeoLineSegment geoLineSegment, double error){
        double line_lon1 = geoLineSegment.getGeoCoordinate1().getLon();
        double line_lat1 = geoLineSegment.getGeoCoordinate1().getLat();
        double line_lon2 = geoLineSegment.getGeoCoordinate2().getLon();
        double line_lat2 = geoLineSegment.getGeoCoordinate2().getLat();
        double point_lon = geoCoordinate.getLon();
        double point_lat = geoCoordinate.getLat();

        if (line_lon1 == line_lon2){
            /**
             * 斜率不存在时
             */
            if (line_lon1 == point_lon &&  point_lat <= Math.max(line_lat1,line_lat2) &&  point_lat >= Math.min(line_lat1,line_lat2)){
                return true;
            }else {
                return false;
            }
        }else {
            /**
             * 斜率和截距
             */
            double slope = (line_lat1 - line_lat2) / (line_lon1 - line_lon2);
            double intercept = line_lat1 - (slope * line_lon1);
            /**
             * 比较计算值与实际值
             */
            double Calculated = slope * point_lon + intercept;
            if (Math.abs(Calculated - point_lat) <= Math.abs(error)
                    &&  point_lon <= Math.max(line_lon1,line_lon2)
                    &&  point_lon >= Math.min(line_lon1,line_lon2)
                    &&  point_lat <= Math.max(line_lat1,line_lat2)
                    &&  point_lat >= Math.min(line_lat1,line_lat2)
            ){
                return true;
            }else {
                return false;
            }
        }

    }
}
