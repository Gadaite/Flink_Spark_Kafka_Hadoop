package WorkAoutSpark.Main20220610;

import org.apache.spark.api.java.function.Function;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import scala.Serializable;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrajectoryFunction implements Function<Tuple2<Integer, Iterable<BrightkiteTotalcheckins>>, Brightkite2008>, Serializable {
    @Override
    public Brightkite2008 call(Tuple2<Integer, Iterable<BrightkiteTotalcheckins>> v1) throws Exception {
        Integer userId = v1._1;
        List<Date> dates = new ArrayList<>();
        List<Coordinate> cord = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        v1._2.forEach(x ->{
            dates.add(x.getTime());
            Double lon = x.getLongitude();
            Double lat = x.getLatitude();
            cord.add(new Coordinate(lon,lat));
        });
        LineString line = geometryFactory.createLineString(cord.toArray(new Coordinate[dates.size()]));
        Brightkite2008 obj = new Brightkite2008(userId, dates.get(0), dates.get(dates.size() - 1), line);
        return obj;
    }
}
