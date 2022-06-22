package WorkAoutSpark.Main20220621;

import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.util.Date;

/**
 * static和transient修饰的变量不会被序列化，这也是解决序列化问题的方法之一，让不能序列化的引用用static和transient来修饰。
 * static修饰的是类的状态，而不是对象状态，所以不存在序列化问题。
 * transient修饰的变量，是不会被序列化到文件中，在被反序列化后，transient变量的值被设为初始值，如int是0，对象是null
 */
public class ResultFormat implements Serializable {
    private java.lang.Integer user;
    private java.util.Date startTime;
    private java.util.Date endTime;
    private transient Geometry trajectory;

    public ResultFormat(Integer user, Date startTime, Date endTime, Geometry trajectory) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trajectory = trajectory;
    }
    public ResultFormat(){}

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

    public Geometry getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(Geometry trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public String toString() {
        return "ResultFormat{" +
                "user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", trajectory=" + trajectory +
                '}';
    }
}
