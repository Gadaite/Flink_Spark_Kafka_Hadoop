package WorkAoutSpark.Main20220625;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * 测试不同坐标系下的距离
 */
public class CRSverification {
    public static void main(String[] args) throws FactoryException, TransformException {
        Coordinate A = new Coordinate(0, 0);
        Coordinate B = new Coordinate(180, 0);
        CoordinateReferenceSystem system1 = CRS.decode("CRS:84", true);
        CoordinateReferenceSystem system2 = CRS.decode("EPSG:3857",true);// 该坐标系国内计算精准，但是全球误差很大
//        CoordinateReferenceSystem system2 = CRS.decode("EPSG:2437",true);// 该坐标系全球计算一般，小面积计算误差很大
        GeometryFactory geometryFactory = new GeometryFactory();
        MathTransform mathTransform = CRS.findMathTransform(system1, system2, false);
        LineString lineString1 = geometryFactory.createLineString(new Coordinate[]{A,B});
        Geometry transformedLineString1 = JTS.transform(lineString1, mathTransform);
        System.out.println(transformedLineString1.getLength()*2);
        LineString lineString = geometryFactory.createLineString(new Coordinate[]{new Coordinate(116.28, 39.54), new Coordinate(104.06, 30.7)});
        Geometry transform = JTS.transform(lineString, mathTransform);
        System.out.println(transform.getLength());
    }
}
