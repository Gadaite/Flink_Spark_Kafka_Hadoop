package WorkAoutSpark.Main20220626;

import java.io.Serializable;

public class TaxiDataOfCar implements Serializable {

    private Integer VehicleNum;//   汽车ID
    private String StartPoint;//    开始点转WKT
    private String EndPoint;//      结束点转WKT
    private Double RunDuration;//   载客时长
    private Double WaitDuration;//  等待时长
    private Double MoveDistance;//  移动路线距离
    private Double AllDuration;//   总时长
    private String LineString;//    轨迹线转WKT
    private Double MaxSpeed;//      最大速度
    private Double MinSpeed;//      最小速度
    private Integer OrderCount;//   订单数量

    public TaxiDataOfCar(Integer vehicleNum, String startPoint, String endPoint, Double runDuration, Double waitDuration, Double moveDistance, Double allDuration, String lineString, Double maxSpeed, Double minSpeed, Integer orderCount) {
        VehicleNum = vehicleNum;
        StartPoint = startPoint;
        EndPoint = endPoint;
        RunDuration = runDuration;
        WaitDuration = waitDuration;
        MoveDistance = moveDistance;
        AllDuration = allDuration;
        LineString = lineString;
        MaxSpeed = maxSpeed;
        MinSpeed = minSpeed;
        OrderCount = orderCount;
    }

    public TaxiDataOfCar(){}

    public Integer getVehicleNum() {
        return VehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        VehicleNum = vehicleNum;
    }

    public String getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(String startPoint) {
        StartPoint = startPoint;
    }

    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String endPoint) {
        EndPoint = endPoint;
    }

    public Double getRunDuration() {
        return RunDuration;
    }

    public void setRunDuration(Double runDuration) {
        RunDuration = runDuration;
    }

    public Double getWaitDuration() {
        return WaitDuration;
    }

    public void setWaitDuration(Double waitDuration) {
        WaitDuration = waitDuration;
    }

    public Double getMoveDistance() {
        return MoveDistance;
    }

    public void setMoveDistance(Double moveDistance) {
        MoveDistance = moveDistance;
    }

    public Double getAllDuration() {
        return AllDuration;
    }

    public void setAllDuration(Double allDuration) {
        AllDuration = allDuration;
    }

    public String getLineString() {
        return LineString;
    }

    public void setLineString(String lineString) {
        LineString = lineString;
    }

    public Double getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public Double getMinSpeed() {
        return MinSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        MinSpeed = minSpeed;
    }

    public Integer getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(Integer orderCount) {
        OrderCount = orderCount;
    }

    @Override
    public String toString() {
        return "TaxiDataOfCar{" +
                "VehicleNum=" + VehicleNum +
                ", StartPoint='" + StartPoint + '\'' +
                ", EndPoint='" + EndPoint + '\'' +
                ", RunDuration=" + RunDuration +
                ", WaitDuration=" + WaitDuration +
                ", MoveDistance=" + MoveDistance +
                ", AllDuration=" + AllDuration +
                ", LineString='" + LineString + '\'' +
                ", MaxSpeed=" + MaxSpeed +
                ", MinSpeed=" + MinSpeed +
                ", OrderCount=" + OrderCount +
                '}';
    }
}
