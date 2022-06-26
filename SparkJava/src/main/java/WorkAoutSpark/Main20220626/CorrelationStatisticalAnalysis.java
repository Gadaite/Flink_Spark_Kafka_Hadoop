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
import java.text.SimpleDateFormat;
import java.util.*;

public class CorrelationStatisticalAnalysis implements Function<Tuple2<Integer, Iterable<Taxidata>>, Tuple2<TaxiDataOfCar,Iterable<TaxiDataOfOrder>>> {
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
    public Tuple2<TaxiDataOfCar, Iterable<TaxiDataOfOrder>> call(Tuple2<Integer, Iterable<Taxidata>> v1) throws Exception {
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
        WKTWriter wktWriter = new WKTWriter();
        GeometryFactory geometryFactory = new GeometryFactory();
        CoordinateReferenceSystem system1 = CRS.decode("CRS:84", true);
        CoordinateReferenceSystem system2 = CRS.decode(CCRRSS,true);
        MathTransform mathTransform = CRS.findMathTransform(system1, system2, false);
        taxiDataList.sort(Comparator.comparing(Taxidata::getStime));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(taxiDataList.get(0).getStime());//   car开始时间
        String format2 = simpleDateFormat.format(taxiDataList.get(taxiDataList.size() - 1).getStime());//   car结束时间
        Timestamp timestamp1 = Timestamp.valueOf(String.valueOf(format1));
        Timestamp timestamp2 = Timestamp.valueOf(String.valueOf(format2));
        long alltime_car = timestamp2.getTime() - timestamp1.getTime();//   car全程总时长
        Double longitude1 = taxiDataList.get(0).getLongitude();
        Double longitude2 = taxiDataList.get(taxiDataList.size() - 1).getLongitude();
        Double latitude1 = taxiDataList.get(0).getLatitude();
        Double latitude2 = taxiDataList.get(taxiDataList.size() - 1).getLatitude();
        Geometry geometry1 = geometryFactory.createGeometry(geometryFactory.createPoint(new Coordinate(longitude1, latitude1)));
        Geometry geometry2 = geometryFactory.createGeometry(geometryFactory.createPoint(new Coordinate(longitude2, latitude2)));
        String startPoint_car = wktWriter.write(geometry1);//   car开始点
        String endPoint_car = wktWriter.write(geometry2);// car结束点

        Double alltime_car_run = 0.0;
        TaxiDataOfCar taxiDataOfCar = new TaxiDataOfCar();
        taxiDataOfCar.setVehicleNum(VehicleNum);
        taxiDataOfCar.setStartPoint(startPoint_car);
        taxiDataOfCar.setEndPoint(endPoint_car);
        taxiDataOfCar.setAllDuration(alltime_car/1000.0);
        TaxiDataOfOrder taxiDataOfOrder = null;
        //  car初始化最大是速度，最小速度
        taxiDataOfCar.setMaxSpeed(taxiDataList.get(0).getSpeed());
        taxiDataOfCar.setMinSpeed(taxiDataList.get(0).getSpeed());
        boolean status = false;
        for (Taxidata data : taxiDataList){
            if (data.getOpenStatus() == 0.0 && !status){
                taxiDataOfOrder = new TaxiDataOfOrder();
                OrderCount = OrderCount + 1;//  car订单个数
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
                //  car持续增加轨迹点
                coordinatesOfCar.add(coordinate);
                //  order设置关联ID
                taxiDataOfOrder.setVehicleNum(VehicleNum);
                //  order初始化最大速度,最小速度
                taxiDataOfOrder.setMaxSpeed(data.getSpeed());
                taxiDataOfOrder.setMinSpeed(data.getSpeed());

                status = true;
            }else if (data.getOpenStatus() == 0.0 && status){
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
                if (data.getSpeed() > taxiDataOfCar.getMaxSpeed()){
                    taxiDataOfCar.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfCar.getMinSpeed()){
                    taxiDataOfCar.setMinSpeed(data.getSpeed());
                }
            }else if (data.getOpenStatus() == 1.0 && status){
                if (data.getSpeed() > taxiDataOfOrder.getMaxSpeed()){
                    taxiDataOfOrder.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfOrder.getMinSpeed() && data.getSpeed() != 0.0){
                    taxiDataOfOrder.setMinSpeed(data.getSpeed());
                }
                if (data.getSpeed() > taxiDataOfCar.getMaxSpeed()){
                    taxiDataOfCar.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfCar.getMinSpeed()){
                    taxiDataOfCar.setMinSpeed(data.getSpeed());
                }
                Double longitude = data.getLongitude();
                Double latitude = data.getLatitude();
                Coordinate coordinate = new Coordinate(longitude, latitude);
                //  order持续更新坐标点集
                coordinatesOfOrder.add(coordinate);
                //  car持续增加轨迹点
                coordinatesOfCar.add(coordinate);
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
                //  car载客时长累计
                alltime_car_run = alltime_car_run + l/1000.0;

                //  根据坐标系计算距离
                double length = JTS.transform(lineString, mathTransform).getLength();
                taxiDataOfOrder.setMoveDistance(length);
                status = false;
                taxiDataOfOrders.add(taxiDataOfOrder);
                //  car持续增加轨迹点
                coordinatesOfCar.add(coordinate);
                coordinatesOfOrder.clear();
            }
            else {
                Double longitude = data.getLongitude();
                Double latitude = data.getLatitude();
                Coordinate coordinate = new Coordinate(longitude, latitude);
                //  car持续增加轨迹点
                coordinatesOfCar.add(coordinate);
                //  car持续更新最大最小速度
                if (data.getSpeed() > taxiDataOfCar.getMaxSpeed()){
                    taxiDataOfCar.setMaxSpeed(data.getSpeed());
                }
                if (data.getSpeed() < taxiDataOfCar.getMinSpeed()){
                    taxiDataOfCar.setMinSpeed(data.getSpeed());
                }
            }
        }
        if (coordinatesOfCar.size() >= 2){
            Geometry line_car = geometryFactory.createGeometry(geometryFactory.
                    createLineString(coordinatesOfCar.toArray(new Coordinate[coordinatesOfCar.size()])));
            String linecar = wktWriter.write(line_car);//   car全程轨迹
            taxiDataOfCar.setLineString(linecar);
            Geometry lineString_car = JTS.transform(line_car, mathTransform);
            double lengthAll = lineString_car.getLength();//    car全程长度
            taxiDataOfCar.setMoveDistance(lengthAll);
        }
        taxiDataList.sort(Comparator.comparing(Taxidata::getSpeed));
        taxiDataOfCar.setOrderCount(OrderCount);    //  car订单个数
        taxiDataOfCar.setRunDuration(alltime_car_run);//    car运行时长
        taxiDataOfCar.setWaitDuration(alltime_car - alltime_car_run); //    car等待时长
        return new Tuple2<>(taxiDataOfCar,taxiDataOfOrders);
    }
}
