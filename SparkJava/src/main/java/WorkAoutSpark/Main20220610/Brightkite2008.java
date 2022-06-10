package WorkAoutSpark.Main20220610;

import org.locationtech.jts.geom.LineString;

import java.io.Serializable;
import java.util.Date;

public class Brightkite2008 implements Serializable {
    private Integer user;
    private Date startTime;
    private Date endTime;
    private LineString trajectory;

    public Brightkite2008(Integer user, Date startTime, Date endTime, LineString trajectory) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trajectory = trajectory;
    }

    public Brightkite2008(){}

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public LineString getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(LineString trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public String toString() {
        return "Brightkite2008{" +
                "user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", trajectory=" + trajectory +
                '}';
    }
}
