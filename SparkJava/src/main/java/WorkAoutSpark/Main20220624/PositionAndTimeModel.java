package WorkAoutSpark.Main20220624;


import org.locationtech.jts.geom.Coordinate;

import java.io.Serializable;
import java.sql.Timestamp;

public class PositionAndTimeModel implements Serializable {
    private Coordinate coordinate;

    private Timestamp timestamp;

    public PositionAndTimeModel(Coordinate coordinate, Timestamp timestamp) {
        this.coordinate = coordinate;
        this.timestamp = timestamp;
    }

    public PositionAndTimeModel(){}

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PositionAndTimeModel{" +
                "coordinate=" + coordinate +
                ", timestamp=" + timestamp +
                '}';
    }
}
