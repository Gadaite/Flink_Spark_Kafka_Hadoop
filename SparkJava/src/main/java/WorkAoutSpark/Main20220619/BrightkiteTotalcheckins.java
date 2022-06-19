package WorkAoutSpark.Main20220619;

import java.io.Serializable;
/**
 * table name:  Brightkite_totalCheckins
 * author name: Gadaite
 * create time: 2022-06-18 21:49:38
 */ 
public class BrightkiteTotalcheckins extends EntityHelper implements Serializable {

	private java.lang.Integer user;
	private java.util.Date time;
	private java.lang.Double latitude;
	private java.lang.Double longitude;
	private java.lang.String location;

	public BrightkiteTotalcheckins() {
		super();
	}
	public BrightkiteTotalcheckins(java.lang.Integer user,java.util.Date time,java.lang.Double latitude,java.lang.Double longitude,java.lang.String location) {
		this.user=user;
		this.time=time;
		this.latitude=latitude;
		this.longitude=longitude;
		this.location=location;
	}
	public void setUser(java.lang.Integer user){
		this.user=user;
	}
	public java.lang.Integer getUser(){
		return user;
	}
	public void setTime(java.util.Date time){
		this.time=time;
	}
	public java.util.Date getTime(){
		return time;
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
	public void setLocation(java.lang.String location){
		this.location=location;
	}
	public java.lang.String getLocation(){
		return location;
	}
	@Override
	public String toString() {
		return "Brightkite_totalCheckins[" + 
			"user=" + user + 
			", time=" + time + 
			", latitude=" + latitude + 
			", longitude=" + longitude + 
			", location=" + location + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

