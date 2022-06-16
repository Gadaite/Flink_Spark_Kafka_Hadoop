package WorkAoutSpark.Main20220615;

import org.locationtech.jts.geom.LineString;

import java.io.Serializable;
import java.util.Date;

/**
 * 统计结果入库表格式
 */
public class TrajectoryByDayModel implements Serializable {
    private Date date;
    private Long duration;
    private LineString line;

    public TrajectoryByDayModel(Date date, Long duration, LineString line) {
        this.date = date;
        this.line = line;
        this.duration = duration;
    }

    public TrajectoryByDayModel(){}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LineString getLine() {
        return line;
    }

    public void setLine(LineString line) {
        this.line = line;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TrajectoryByDayModel{" +
                "date=" + date +
                ", duration=" + duration +
                ", line=" + line +
                '}';
    }
}
