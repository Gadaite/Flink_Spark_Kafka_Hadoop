package WorkAoutSpark.Main20220620;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.*;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class GeoToolTest {
    public static void main(String[] args) throws FactoryException, TransformException {
        //  WGS84坐标
        CoordinateReferenceSystem sourceCRS = CRS.decode("CRS:84");
        //  Pseudo-Mercator(墨卡托投影)
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
        MathTransform mathTransform = CRS.findMathTransform(sourceCRS, targetCRS, false);
        Coordinate coordinate1 = new Coordinate(100.0, 20.2);
        Coordinate coordinate2 = new Coordinate(90.0, 50.3);
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString lineString = geometryFactory.createLineString(new Coordinate[]{coordinate1, coordinate2});
        Geometry transform = JTS.transform(lineString, mathTransform);
        System.out.println(transform.getLength());
        System.out.println(lineString.getLength());
        System.out.println(transform.getLength() / lineString.getLength() / 2);

    }
}
