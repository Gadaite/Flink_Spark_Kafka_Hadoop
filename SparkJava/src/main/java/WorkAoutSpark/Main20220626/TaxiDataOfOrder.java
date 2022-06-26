package WorkAoutSpark.Main20220626;

import java.io.Serializable;
import java.sql.Timestamp;

public class TaxiDataOfOrder implements Serializable {
    private String OrderID;//       订单ID使用不重复的UUID
    private Timestamp StartTime;//  订单起始时间
    private Timestamp EndtTime;//   订单结束时间
    private Integer VehicleNum;//   所乘汽车ID
    private String StartPoint;//    开始点转WKT
    private String EndPoint;//      结束点转WKT
    private Double MoveDistance;//  移动路线距离
    private Double AllDuration;//   总时长
    private String LineString;//    轨迹线转WKT
    private Double MaxSpeed;//      最大速度
    private Double MinSpeed;//      最小速度

    public TaxiDataOfOrder(String OrderID, Integer vehicleNum, String startPoint, String endPoint, Double moveDistance, Double allDuration, String lineString, Double maxSpeed, Double minSpeed, Timestamp startTime ,Timestamp endTime) {
        this.OrderID = OrderID;
        VehicleNum = vehicleNum;
        StartPoint = startPoint;
        EndPoint = endPoint;
        MoveDistance = moveDistance;
        AllDuration = allDuration;
        LineString = lineString;
        MaxSpeed = maxSpeed;
        MinSpeed = minSpeed;
        StartTime = startTime;
        EndtTime = endTime;
    }

    public TaxiDataOfOrder(){}

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

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

    public Timestamp getStartTime() {
        return StartTime;
    }

    public void setStartTime(Timestamp startTime) {
        StartTime = startTime;
    }

    public Timestamp getEndtTime() {
        return EndtTime;
    }

    public void setEndtTime(Timestamp endtTime) {
        EndtTime = endtTime;
    }

    @Override
    public String toString() {
        return "TaxiDataOfOrder{" +
                "OrderID='" + OrderID + '\'' +
                ", StartTime=" + StartTime +
                ", EndtTime=" + EndtTime +
                ", VehicleNum=" + VehicleNum +
                ", StartPoint='" + StartPoint + '\'' +
                ", EndPoint='" + EndPoint + '\'' +
                ", MoveDistance=" + MoveDistance +
                ", AllDuration=" + AllDuration +
                ", LineString='" + LineString + '\'' +
                ", MaxSpeed=" + MaxSpeed +
                ", MinSpeed=" + MinSpeed +
                '}';
    }
}
