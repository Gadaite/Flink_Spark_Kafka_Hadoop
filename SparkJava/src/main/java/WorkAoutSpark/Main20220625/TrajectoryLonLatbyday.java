package WorkAoutSpark.Main20220625;

import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.sql.Timestamp;

public class TrajectoryLonLatbyday implements Serializable {
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer pointCount;
    private Double distance;
    private Geometry linestring;

    public TrajectoryLonLatbyday(Timestamp startTime, Timestamp endTime, Integer pointCount, Double distance, Geometry linestring) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pointCount = pointCount;
        this.distance = distance;
        this.linestring = linestring;
    }
    public TrajectoryLonLatbyday(){}

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Geometry getLinestring() {
        return linestring;
    }

    public void setLinestring(Geometry linestring) {
        this.linestring = linestring;
    }

    @Override
    public String toString() {
        return "Trajectlonlatbyday{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", pointCount=" + pointCount +
                ", distance=" + distance +
                ", linestring=" + linestring +
                '}';
    }
}
