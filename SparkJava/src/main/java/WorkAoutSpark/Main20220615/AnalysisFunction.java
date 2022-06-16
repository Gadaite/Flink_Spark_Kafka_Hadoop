package WorkAoutSpark.Main20220615;


import org.apache.spark.api.java.function.Function;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import scala.Serializable;
import scala.Tuple2;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 测试数据可用性，只使用经纬度数据
 */
public class AnalysisFunction implements Function<Tuple2<String, Iterable<Trajectlonlat>>, TrajectoryByDayModel>, Serializable {
    @Override
    public TrajectoryByDayModel call(Tuple2<String, Iterable<Trajectlonlat>> tuple2) throws Exception {
        String date = tuple2._1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        Date day = simpleDateFormat.parse(date);
        /**
         * 迭代取出数据
         */
        List<Trajectlonlat> trajectlonlats = new ArrayList<>();
        tuple2._2.forEach(line ->{
            trajectlonlats.add(line);
        });
        /**
         * 按照时间进行排序
         */
        trajectlonlats.sort(Comparator.comparing(Trajectlonlat::getDatetime));
        /**
         * 主要计算和转换
         */
        Date datetime0 = trajectlonlats.get(0).getDatetime();
        Timestamp startTime = Timestamp.valueOf(datetime0.toString().split("\\.")[0]);
        Date datetime1 = trajectlonlats.get(trajectlonlats.size() - 1).getDatetime();
        Timestamp endTime = Timestamp.valueOf(datetime1.toString().split("\\.")[0]);
        long duration = endTime.getTime() - startTime.getTime();
        /**
         * JTS转换
         */
        List<Coordinate> list = new ArrayList<>();
        for (Trajectlonlat point : trajectlonlats){
            Coordinate coordinate = new Coordinate(point.getLongitude(), point.getLatitude());
            list.add(coordinate);
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString lineString = geometryFactory.createLineString(list.toArray(new Coordinate[list.size()]));
        return new TrajectoryByDayModel(day,duration,lineString);
    }
}
