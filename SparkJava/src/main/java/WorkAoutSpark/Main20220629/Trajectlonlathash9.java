package WorkAoutSpark.Main20220629;

import WorkAoutSpark.Main20220629.EntityHelper;

import java.io.Serializable;

/**
 * table name:  trajectlonlathash9
 * author name: Gadaite
 * create time: 2022-06-28 23:14:17
 */ 
public class Trajectlonlathash9 extends EntityHelper implements Serializable {

	private Double latitude;
	private Double longitude;
	private Double altitude;
	private java.util.Date datetime;

	public Trajectlonlathash9() {
		super();
	}
	public Trajectlonlathash9(Double latitude, Double longitude, Double altitude, java.util.Date datetime) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
		this.datetime=datetime;
	}
	public void setLatitude(Double latitude){
		this.latitude=latitude;
	}
	public Double getLatitude(){
		return latitude;
	}
	public void setLongitude(Double longitude){
		this.longitude=longitude;
	}
	public Double getLongitude(){
		return longitude;
	}
	public void setAltitude(Double altitude){
		this.altitude=altitude;
	}
	public Double getAltitude(){
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

