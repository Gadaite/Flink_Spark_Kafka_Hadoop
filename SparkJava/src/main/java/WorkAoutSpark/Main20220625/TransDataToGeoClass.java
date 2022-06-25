package WorkAoutSpark.Main20220625;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKTReader;

import java.sql.Timestamp;

public class TransDataToGeoClass implements Function<Row,TrajectoryLonLatbyday> {
    @Override
    public TrajectoryLonLatbyday call(Row v1) throws Exception {
        double distance = v1.getDouble(v1.fieldIndex("distance"));
        Timestamp startTime = v1.getTimestamp(v1.fieldIndex("startTime"));
        Timestamp endTime = v1.getTimestamp(v1.fieldIndex("endTime"));
        String linestring = v1.getString(v1.fieldIndex("linestring"));
        int pointCount = v1.getInt(v1.fieldIndex("pointCount"));
        byte[] bytes = WKBReader.hexToBytes(linestring);
        Geometry readgeo = new WKBReader().read(bytes);
        return new TrajectoryLonLatbyday(startTime,endTime,pointCount,distance,readgeo);
    }
}
