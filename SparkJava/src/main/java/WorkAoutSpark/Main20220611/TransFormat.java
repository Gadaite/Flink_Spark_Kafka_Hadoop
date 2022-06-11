package WorkAoutSpark.Main20220611;

import GadaiteToolGeo.StrLineSTRING;
import org.apache.spark.api.java.function.Function;
import org.locationtech.jts.geom.LineString;

import java.util.Date;

/**
 * 调用自定义的工具类，将字符串的轨迹转换成JTS的LineString类型
 */
public class TransFormat implements Function<Brightkitetrajectory2008, Format2008> {
    @Override
    public Format2008 call(Brightkitetrajectory2008 v1) throws Exception {
        Integer user = v1.getUser();
        Date startTime = v1.getStartTime();
        Date endTime = v1.getEndTime();
        String trajectory = v1.getTrajectory();
        LineString lineString = new StrLineSTRING().FormatToLineString(trajectory);
        return new Format2008(user,startTime,endTime,lineString);
    }
}
