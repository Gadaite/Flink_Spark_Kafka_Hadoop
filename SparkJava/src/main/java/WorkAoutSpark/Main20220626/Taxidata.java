package WorkAoutSpark.Main20220626;

import java.io.Serializable;
/**
 * table name:  taxidata
 * author name: Gadaite
 * create time: 2022-06-26 13:36:02
 */ 
public class Taxidata extends EntityHelper implements Serializable {

	private java.lang.Double latitude;
	private java.lang.Double longitude;
	private java.lang.Double openStatus;
	private java.lang.Double speed;
	private java.util.Date stime;
	private java.lang.Integer vehicleNum;

	public Taxidata() {
		super();
	}
	public Taxidata(java.lang.Double latitude,java.lang.Double longitude,java.lang.Double openStatus,java.lang.Double speed,java.util.Date stime,java.lang.Integer vehicleNum) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.openStatus=openStatus;
		this.speed=speed;
		this.stime=stime;
		this.vehicleNum=vehicleNum;
	}
	public void setLatitude(java.lang.Double latitude){
		this.latitude=latitude;
	}
	public java.lang.Double getLatitude(){
		return latitude;
	}
	public void setLongitude(java.lang.Double longitude){
		this.longitude=longitude;
	}
	public java.lang.Double getLongitude(){
		return longitude;
	}
	public void setOpenStatus(java.lang.Double openStatus){
		this.openStatus=openStatus;
	}
	public java.lang.Double getOpenStatus(){
		return openStatus;
	}
	public void setSpeed(java.lang.Double speed){
		this.speed=speed;
	}
	public java.lang.Double getSpeed(){
		return speed;
	}
	public void setStime(java.util.Date stime){
		this.stime=stime;
	}
	public java.util.Date getStime(){
		return stime;
	}
	public void setVehicleNum(java.lang.Integer vehicleNum){
		this.vehicleNum=vehicleNum;
	}
	public java.lang.Integer getVehicleNum(){
		return vehicleNum;
	}
	@Override
	public String toString() {
		return "taxidata[" + 
			"latitude=" + latitude + 
			", longitude=" + longitude + 
			", openStatus=" + openStatus + 
			", speed=" + speed + 
			", stime=" + stime + 
			", vehicleNum=" + vehicleNum + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

