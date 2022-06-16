package GadaiteJavaBeanFromDBS;

import java.io.Serializable;
/**
 * table name:  trajectlonlat
 * author name: Gadaite
 * create time: 2022-06-15 23:50:59
 */ 
public class Trajectlonlat extends EntityHelper implements Serializable {

	private java.lang.Double latitude;
	private java.lang.Double longitude;
	private java.lang.Double altitude;
	private java.util.Date datetime;

	public Trajectlonlat() {
		super();
	}
	public Trajectlonlat(java.lang.Double latitude,java.lang.Double longitude,java.lang.Double altitude,java.util.Date datetime) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
		this.datetime=datetime;
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
	public void setAltitude(java.lang.Double altitude){
		this.altitude=altitude;
	}
	public java.lang.Double getAltitude(){
		return altitude;
	}
	public void setDatetime(java.util.Date datetime){
		this.datetime=datetime;
	}
	public java.util.Date getDatetime(){
		return datetime;
	}
	@Override
	public String toString() {
		return "trajectlonlat[" + 
			"latitude=" + latitude + 
			", longitude=" + longitude + 
			", altitude=" + altitude + 
			", datetime=" + datetime + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

