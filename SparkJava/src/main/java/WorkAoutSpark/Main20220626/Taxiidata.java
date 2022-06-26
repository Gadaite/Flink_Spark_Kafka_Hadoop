package WorkAoutSpark.Main20220626;

import java.io.Serializable;

public class Taxiidata implements Serializable {
    private Integer VehicleNum;
    private String Stime;
    private Double longitude;
    private Double latitude;
    private Double OpenStatus;
    private Double Speed;

    public Taxiidata(Integer vehicleNum, String stime, Double longitude, Double latitude, Double openStatus, Double speed) {
        VehicleNum = vehicleNum;
        Stime = stime;
        this.longitude = longitude;
        this.latitude = latitude;
        OpenStatus = openStatus;
        Speed = speed;
    }
    public Taxiidata(){}

    public Integer getVehicleNum() {
        return VehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        VehicleNum = vehicleNum;
    }

    public String getStime() {
        return Stime;
    }

    public void setStime(String stime) {
        Stime = stime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getOpenStatus() {
        return OpenStatus;
    }

    public void setOpenStatus(Double openStatus) {
        OpenStatus = openStatus;
    }

    public Double getSpeed() {
        return Speed;
    }

    public void setSpeed(Double speed) {
        Speed = speed;
    }

    @Override
    public String toString() {
        return "TaxiiData{" +
                "VehicleNum=" + VehicleNum +
                ", Stime='" + Stime + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", OpenStatus=" + OpenStatus +
                ", Speed=" + Speed +
                '}';
    }
}
