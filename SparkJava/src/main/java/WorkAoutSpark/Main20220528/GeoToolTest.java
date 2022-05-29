package WorkAoutSpark.Main20220528;


import GadaiteToolGeo.*;
public class GeoToolTest {
    public static void main(String[] args) {
        String geoHash = new LonLatToGeoHash().getGeoHash(116.389550, 39.928167, 9);
        System.out.println("116.389550, 39.928167 `s GeoHash ----" + geoHash);
        String spaceCoordinate = new GeoHashToLonLat().GetSpaceCoordinate(geoHash);
        System.out.println(geoHash + " `s lon,lat ----" + spaceCoordinate);
        GeoLineSegment geoLineSegment = new GeoLineSegment(new GeoCoordinate(10, 20),
                new GeoCoordinate(20, 40));
        GeoLineStraight geoLineStraight = geoLineSegment.ConvertToStraightLineModel();
        System.out.println("Slope ----" + geoLineStraight.getSlope());
        System.out.println("Intercept ----" + geoLineStraight.getIntercept());
        GeoCoordinate geoCoordinate = new GeoCoordinate(30, 60);
        boolean b = new GeoIFPointOnLineSegment().PointOnSegmentLine(geoCoordinate, geoLineSegment, 0.0);
        boolean b1 = new GeoIFPointOnLineStraight().PointOnStraightLine(geoCoordinate, geoLineSegment, 0.0);
        System.out.println("30,60 on segment ----" + b);
        System.out.println("30,60 on straight ----" + b1);

    }
}
