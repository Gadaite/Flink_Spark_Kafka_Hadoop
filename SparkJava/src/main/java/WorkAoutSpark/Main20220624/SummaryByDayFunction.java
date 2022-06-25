package WorkAoutSpark.Main20220624;

import org.apache.spark.api.java.function.Function;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import scala.Tuple2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 转换坐标系进行数据的统计
 */
public class SummaryByDayFunction implements Function<Tuple2<String, Iterable<PositionAndTimeModel>>, SummaryByDayModel> {
    @Override
    public SummaryByDayModel call(Tuple2<String, Iterable<PositionAndTimeModel>> v1) throws Exception {
        List<PositionAndTimeModel> list = new ArrayList<>();
        List<Coordinate> coordinateList = new ArrayList<>();
        v1._2.forEach(x ->{
            list.add(x);
            coordinateList.add(x.getCoordinate());
        });
        Timestamp startTime = list.get(0).getTimestamp();
        Timestamp endTime = list.get(list.size() - 1).getTimestamp();
        //  WGS84坐标
        CoordinateReferenceSystem sourceCRS = CRS.decode("CRS:84");
        //  Pseudo-Mercator(墨卡托投影)
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
        MathTransform mathTransform = CRS.findMathTransform(sourceCRS, targetCRS, false);
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString lineString = geometryFactory.createLineString(coordinateList.toArray(new Coordinate[coordinateList.size()]));
        Geometry transformedLineString = JTS.transform(lineString, mathTransform);
        double length = transformedLineString.getLength();
        int pointCount = coordinateList.size();
        return new SummaryByDayModel(startTime,endTime,pointCount,length);
    }
}
