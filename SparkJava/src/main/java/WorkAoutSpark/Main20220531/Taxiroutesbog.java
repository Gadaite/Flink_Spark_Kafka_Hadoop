package WorkAoutSpark.Main20220531;

import WorkAoutSpark.Main20220530.EntityHelper;

import java.io.Serializable;

/**
 * table name:  TaxiRoutesBoG
 * author name: Gadaite
 * create time: 2022-05-30 21:09:04
 */ 
public class Taxiroutesbog extends EntityHelper implements Serializable {

	private Integer id;
	private String vendor_id;
	private String pickup_datetime;
	private String dropoff_datetime;
	private Double pickup_longitude;
	private Double pickup_latitude;
	private Double dropoff_longitude;
	private Double dropoff_latitude;
	private String store_and_fwd_flag;
	private Integer trip_duration;
	private Integer dist_meters;
	private Integer wait_sec;

	public Taxiroutesbog() {
		super();
	}
	public Taxiroutesbog(Integer id, String vendor_id, String pickup_datetime, String dropoff_datetime, Double pickup_longitude, Double pickup_latitude, Double dropoff_longitude, Double dropoff_latitude, String store_and_fwd_flag, Integer trip_duration, Integer dist_meters, Integer wait_sec) {
		this.id=id;
		this.vendor_id=vendor_id;
		this.pickup_datetime=pickup_datetime;
		this.dropoff_datetime=dropoff_datetime;
		this.pickup_longitude=pickup_longitude;
		this.pickup_latitude=pickup_latitude;
		this.dropoff_longitude=dropoff_longitude;
		this.dropoff_latitude=dropoff_latitude;
		this.store_and_fwd_flag=store_and_fwd_flag;
		this.trip_duration=trip_duration;
		this.dist_meters=dist_meters;
		this.wait_sec=wait_sec;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setVendor_id(String vendor_id){
		this.vendor_id=vendor_id;
	}
	public String getVendor_id(){
		return vendor_id;
	}
	public void setPickup_datetime(String pickup_datetime){
		this.pickup_datetime=pickup_datetime;
	}
	public String getPickup_datetime(){
		return pickup_datetime;
	}
	public void setDropoff_datetime(String dropoff_datetime){
		this.dropoff_datetime=dropoff_datetime;
	}
	public String getDropoff_datetime(){
		return dropoff_datetime;
	}
	public void setPickup_longitude(Double pickup_longitude){
		this.pickup_longitude=pickup_longitude;
	}
	public Double getPickup_longitude(){
		return pickup_longitude;
	}
	public void setPickup_latitude(Double pickup_latitude){
		this.pickup_latitude=pickup_latitude;
	}
	public Double getPickup_latitude(){
		return pickup_latitude;
	}
	public void setDropoff_longitude(Double dropoff_longitude){
		this.dropoff_longitude=dropoff_longitude;
	}
	public Double getDropoff_longitude(){
		return dropoff_longitude;
	}
	public void setDropoff_latitude(Double dropoff_latitude){
		this.dropoff_latitude=dropoff_latitude;
	}
	public Double getDropoff_latitude(){
		return dropoff_latitude;
	}
	public void setStore_and_fwd_flag(String store_and_fwd_flag){
		this.store_and_fwd_flag=store_and_fwd_flag;
	}
	public String getStore_and_fwd_flag(){
		return store_and_fwd_flag;
	}
	public void setTrip_duration(Integer trip_duration){
		this.trip_duration=trip_duration;
	}
	public Integer getTrip_duration(){
		return trip_duration;
	}
	public void setDist_meters(Integer dist_meters){
		this.dist_meters=dist_meters;
	}
	public Integer getDist_meters(){
		return dist_meters;
	}
	public void setWait_sec(Integer wait_sec){
		this.wait_sec=wait_sec;
	}
	public Integer getWait_sec(){
		return wait_sec;
	}
	@Override
	public String toString() {
		return "TaxiRoutesBoG[" + 
			"id=" + id + 
			", vendor_id=" + vendor_id + 
			", pickup_datetime=" + pickup_datetime + 
			", dropoff_datetime=" + dropoff_datetime + 
			", pickup_longitude=" + pickup_longitude + 
			", pickup_latitude=" + pickup_latitude + 
			", dropoff_longitude=" + dropoff_longitude + 
			", dropoff_latitude=" + dropoff_latitude + 
			", store_and_fwd_flag=" + store_and_fwd_flag + 
			", trip_duration=" + trip_duration + 
			", dist_meters=" + dist_meters + 
			", wait_sec=" + wait_sec + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

