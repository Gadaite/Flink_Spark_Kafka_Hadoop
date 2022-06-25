package WorkAoutSpark.Main20220624;

import org.apache.spark.api.java.function.Function;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransToPosTimeFunction implements Function<Trajectlonlat, PositionAndTimeModel> {
    @Override
    public PositionAndTimeModel call(Trajectlonlat v1) throws Exception {
        Double longitude = v1.getLongitude();
        Double latitude = v1.getLatitude();
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Timestamp timestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(v1.getDatetime()));
        return new PositionAndTimeModel(coordinate,timestamp);
    }
}
