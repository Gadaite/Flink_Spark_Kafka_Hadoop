package WorkAoutSpark.Main20220624;

import java.io.Serializable;
import java.sql.Timestamp;

public class SummaryByDayModel implements Serializable {
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer pointCount;
    private Double distance;

    public SummaryByDayModel(Timestamp startTime, Timestamp endTime, Integer pointCount, Double distance) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pointCount = pointCount;
        this.distance = distance;
    }
    public SummaryByDayModel(){}

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

    @Override
    public String toString() {
        return "SummaryByDayModel{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", pointCount=" + pointCount +
                ", distance=" + distance +
                '}';
    }
}
