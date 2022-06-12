package WorkAoutSpark.Main20220613;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 *  转换为JTS对应的空间几何类型
 *  并用Entity实体类进行实例化
 */
public class TransToEntity implements Function<Row, CityBoundaryModel> {
    @Override
    public CityBoundaryModel call(Row v1) throws Exception {
        String s = v1.toString();
        String replace = s.replace("[", "").replace("]", "").replace(" ","");
        String[] split = replace.split(",");
        GeometryFactory geometryFactory = new GeometryFactory();
        int adcode = Integer.parseInt(split[0]);
        String name = String.valueOf(split[1]);
        Point center = geometryFactory.createPoint(new Coordinate(Double.valueOf(split[2]), Double.valueOf(split[3])));
        Point centroid = geometryFactory.createPoint(new Coordinate(Double.valueOf(split[4]), Double.valueOf(split[5])));
        int childrenNum = Integer.parseInt(split[6]);
        String level = String.valueOf(split[7]);
        int parentCenter = Integer.parseInt(split[8]);
        int subFeatureIndex = Integer.parseInt(split[9]);
        int acroutes = Integer.parseInt(split[10]);
        List<Double> coord = new ArrayList<>();
        for (int i= 11;i<split.length;i++){
            coord.add(Double.valueOf(split[i]));
        }
        List<Coordinate> coordinateslist = new ArrayList<>();
        for (int i=0;i<coord.size()/2;i++){
            coordinateslist.add(new Coordinate(coord.get(2*i),coord.get(2*i +1)));
        }
        Polygon geometry = geometryFactory.createPolygon(coordinateslist.toArray(new Coordinate[coordinateslist.size()]));
        return new CityBoundaryModel(adcode,name,center,centroid,childrenNum,level,parentCenter,subFeatureIndex,acroutes,geometry);
    }
}
