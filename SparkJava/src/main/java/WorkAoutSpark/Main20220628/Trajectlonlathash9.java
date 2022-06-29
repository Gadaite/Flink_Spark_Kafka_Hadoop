package WorkAoutSpark.Main20220628;

import java.io.Serializable;
/**
 * table name:  trajectlonlathash9
 * author name: Gadaite
 * create time: 2022-06-28 23:14:17
 */ 
public class Trajectlonlathash9 extends EntityHelper implements Serializable {

	private java.lang.Double latitude;
	private java.lang.Double longitude;
	private java.lang.Double altitude;
	private java.util.Date datetime;

	public Trajectlonlathash9() {
		super();
	}
	public Trajectlonlathash9(java.lang.Double latitude,java.lang.Double longitude,java.lang.Double altitude,java.util.Date datetime) {
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
		return "trajectlonlathash9[" + 
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

