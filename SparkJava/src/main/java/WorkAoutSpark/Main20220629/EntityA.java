package WorkAoutSpark.Main20220629;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.sql.Timestamp;

public class EntityA implements Serializable {
    private Double allDuration;
    private Geometry endPoint;
    private Timestamp endtTime;
    private Geometry lineString;

    public EntityA(Double allDuration, Geometry endPoint, Timestamp endtTime, Geometry lineString) {
        this.allDuration = allDuration;
        this.endPoint = endPoint;
        this.endtTime = endtTime;
        this.lineString = lineString;
    }

    public Double getAllDuration() {
        return allDuration;
    }

    public void setAllDuration(Double allDuration) {
        this.allDuration = allDuration;
    }

    public Geometry getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Geometry endPoint) {
        this.endPoint = endPoint;
    }

    public Timestamp getEndtTime() {
        return endtTime;
    }

    public void setEndtTime(Timestamp endtTime) {
        this.endtTime = endtTime;
    }

    public Geometry getLineString() {
        return lineString;
    }

    public void setLineString(Geometry lineString) {
        this.lineString = lineString;
    }

    @Override
    public String toString() {
        return "EntityA{" +
                "allDuration=" + allDuration +
                "@ endPoint=" + endPoint +
                "@ endtTime=" + endtTime +
                "@ lineString=" + lineString +
                '}';
    }
}
