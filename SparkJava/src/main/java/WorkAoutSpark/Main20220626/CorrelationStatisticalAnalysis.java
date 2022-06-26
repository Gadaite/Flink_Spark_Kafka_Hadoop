package WorkAoutSpark.Main20220626;


import org.apache.spark.api.java.function.Function;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.io.WKTWriter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import scala.Tuple2;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class CorrelationStatisticalAnalysis implements Function<Tuple2<Integer, Iterable<Taxidata>>, Tuple2<Iterable<TaxiDataOfCar>,Iterable<TaxiDataOfOrder>>> {
    private String CCRRSS;

    public CorrelationStatisticalAnalysis(String CCRRSS) {
        this.CCRRSS = CCRRSS;
    }
    public CorrelationStatisticalAnalysis(){
        this.CCRRSS = "EPSG:3857";
    }

    public String getCCRRSS() {
        return CCRRSS;
    }

    public void setCCRRSS(String CCRRSS) {
        this.CCRRSS = CCRRSS;
    }

    @Override
    public Tuple2<Iterable<TaxiDataOfCar>, Iterable<TaxiDataOfOrder>> call(Tuple2<Integer, Iterable<Taxidata>> v1) throws Exception {
        Integer VehicleNum = v1._1;
        List<TaxiDataOfCar> taxiDataOfCars = new ArrayList<>();
        List<TaxiDataOfOrder> taxiDataOfOrders = new ArrayList<>();
        List<Coordinate> coordinatesOfCar = new ArrayList<>();
        List<Coordinate> coordinatesOfOrder = new ArrayList<Coordinate>();
        List<Taxidata> taxiDataList = new ArrayList<>();
        Integer OrderCount = 0;
        /**
         * 组内的数据加载到taxiDataList中
         * 按照时间进行排序
         */
        v1._2.forEach(x ->{
            taxiDataList.add(x);
        });
        taxiDataList.sort(Comparator.comparing(Taxidata::getStime));
        Date startTime_car= taxiDataList.get(0).getStime();
        Date endTime_car= taxiDataList.get(taxiDataList.size() - 1).getStime();
        WKTWriter wktWriter = new WKTWriter();
        GeometryFactory geometryFactory = new GeometryFactory();
        TaxiDataOfCar taxiDataOfCar = new TaxiDataOfCar();
        TaxiDataOfOrder taxiDataOfOrder = new TaxiDataOfOrder();
        boolean status = false;
        for (Taxidata data : taxiDataList){
            if (data.getOpenStatus() == 1.0 && !status){
                OrderCount = OrderCount + 1;
                //  order设置随机的orderID
                taxiDataOfOrder.setOrderID(UUID.randomUUID().toString());
                //  order设置起始时间
                taxiDataOfOrder.setStartTime(Timestamp.valueOf(data.getStime().toString()));
                Double longitude = data.getLongitude();
                Double latitude = data.getLatitude();
                Coordinate coordinate = new Coordinate(longitude, latitude);
                Geometry startPoint = geometryFactory.createGeometry(geometryFactory.createPoint(coordinate));
                String startpointStr = wktWriter.write(startPoint);
                //  order设置起始点
                taxiDataOfOrder.setStartPoint(startpointStr);
                coordinatesOfOrder.add(coordinate);
                //  order设置关联ID
                taxiDataOfOrder.setVehicleNum(VehicleNum);
                //  order初始化最大速度,最小速度
                taxiDataOfOrder.setMaxSpeed(data.getSpeed());
                taxiDataOfOrder.setMinSpeed(data.getSpeed());
                //  order
                status = true;
            }else if (data.getOpenStatus() == 1.0 && status){
                Double longitude = data.getLongitude();
                Double latitude = data.getLatitude();
                Coordinate coordinate = new Coordinate(longitude, latitude);
                //  持续更新坐标点集
                coordinatesOfOrder.add(coordinate);
                //  比较计算出最大速度以及最小速度
                if (data.getSpeed() > taxiDataOfOrder.getMaxSpeed()){
                    taxiDataOfOrder.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfOrder.getMinSpeed()){
                    taxiDataOfOrder.setMinSpeed(data.getSpeed());
                }
            }else if (data.getOpenStatus() == 0.0 && status){
                if (data.getSpeed() > taxiDataOfOrder.getMaxSpeed()){
                    taxiDataOfOrder.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfOrder.getMinSpeed() && data.getSpeed() != 0.0){
                    taxiDataOfOrder.setMinSpeed(data.getSpeed());
                }
                Double longitude = data.getLongitude();
                Double latitude = data.getLatitude();
                Coordinate coordinate = new Coordinate(longitude, latitude);
                //  持续更新坐标点集
                coordinatesOfOrder.add(coordinate);
                //  order设置结束点
                taxiDataOfOrder.setEndPoint(wktWriter.write(geometryFactory.createGeometry(geometryFactory.createPoint(coordinate))));
                //  order设置结束时间
                taxiDataOfOrder.setEndtTime(Timestamp.valueOf(data.getStime().toString()));
                //  order设置轨迹线
                LineString lineString = geometryFactory.createLineString(coordinatesOfOrder.toArray(new Coordinate[coordinatesOfOrder.size()]));
                Geometry geometry = geometryFactory.createGeometry(lineString);
                String linestring = wktWriter.write(geometry);
                taxiDataOfOrder.setLineString(linestring);
                //  oder设置总时长
                long l = taxiDataOfOrder.getEndtTime().getTime() - taxiDataOfOrder.getStartTime().getTime();
                taxiDataOfOrder.setAllDuration(l/1000.0);
                //  根据坐标系计算距离
                CoordinateReferenceSystem system1 = CRS.decode("CRS:84", true);
                CoordinateReferenceSystem system2 = CRS.decode(CCRRSS,true);
                MathTransform mathTransform = CRS.findMathTransform(system1, system2, false);
                double length = JTS.transform(lineString, mathTransform).getLength();
                taxiDataOfOrder.setMoveDistance(length);
                status = false;
                taxiDataOfOrders.add(taxiDataOfOrder);
                coordinatesOfOrder.clear();
            }
            else {

            }
        }
        return null;
    }
}
