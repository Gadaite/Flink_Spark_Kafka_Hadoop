package GadaiteToolGeo;

public class GeoIFPointOnLineStraight {
    private GeoLineStraight geoLineStraight;
    private GeoCoordinate geoCoordinate;

    public GeoLineStraight getGeoLineStraight() {
        return geoLineStraight;
    }

    public void setGeoLineStraight(GeoLineStraight geoLineStraight) {
        this.geoLineStraight = geoLineStraight;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }
    public GeoIFPointOnLineStraight(){}
    public GeoIFPointOnLineStraight(GeoLineStraight geoLineStraight, GeoCoordinate geoCoordinate) {
        this.geoLineStraight = geoLineStraight;
        this.geoCoordinate = geoCoordinate;
    }
    public boolean PointOnStraightLine(GeoCoordinate geoCoordinate, GeoLineSegment geoLineSegment, double error){
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
            if (point_lon == line_lon1){
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
            if (Math.abs(Calculated - point_lat) <= Math.abs(error)){
                return true;
            }else {
                return false;
            }
        }
    }
}
