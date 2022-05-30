package WorkAoutSpark.Main20220530;

/**
 * table name:  TaxiRoutesBoG
 * author name: Gadaite
 * create time: 2022-05-30 21:09:04
 */ 
public class Taxiroutesbog extends EntityHelper{

	private java.lang.Integer id;
	private java.lang.String vendor_id;
	private java.lang.String pickup_datetime;
	private java.lang.String dropoff_datetime;
	private java.lang.Double pickup_longitude;
	private java.lang.Double pickup_latitude;
	private java.lang.Double dropoff_longitude;
	private java.lang.Double dropoff_latitude;
	private java.lang.String store_and_fwd_flag;
	private java.lang.Integer trip_duration;
	private java.lang.Integer dist_meters;
	private java.lang.Integer wait_sec;

	public Taxiroutesbog() {
		super();
	}
	public Taxiroutesbog(java.lang.Integer id,java.lang.String vendor_id,java.lang.String pickup_datetime,java.lang.String dropoff_datetime,java.lang.Double pickup_longitude,java.lang.Double pickup_latitude,java.lang.Double dropoff_longitude,java.lang.Double dropoff_latitude,java.lang.String store_and_fwd_flag,java.lang.Integer trip_duration,java.lang.Integer dist_meters,java.lang.Integer wait_sec) {
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
	public void setId(java.lang.Integer id){
		this.id=id;
	}
	public java.lang.Integer getId(){
		return id;
	}
	public void setVendor_id(java.lang.String vendor_id){
		this.vendor_id=vendor_id;
	}
	public java.lang.String getVendor_id(){
		return vendor_id;
	}
	public void setPickup_datetime(java.lang.String pickup_datetime){
		this.pickup_datetime=pickup_datetime;
	}
	public java.lang.String getPickup_datetime(){
		return pickup_datetime;
	}
	public void setDropoff_datetime(java.lang.String dropoff_datetime){
		this.dropoff_datetime=dropoff_datetime;
	}
	public java.lang.String getDropoff_datetime(){
		return dropoff_datetime;
	}
	public void setPickup_longitude(java.lang.Double pickup_longitude){
		this.pickup_longitude=pickup_longitude;
	}
	public java.lang.Double getPickup_longitude(){
		return pickup_longitude;
	}
	public void setPickup_latitude(java.lang.Double pickup_latitude){
		this.pickup_latitude=pickup_latitude;
	}
	public java.lang.Double getPickup_latitude(){
		return pickup_latitude;
	}
	public void setDropoff_longitude(java.lang.Double dropoff_longitude){
		this.dropoff_longitude=dropoff_longitude;
	}
	public java.lang.Double getDropoff_longitude(){
		return dropoff_longitude;
	}
	public void setDropoff_latitude(java.lang.Double dropoff_latitude){
		this.dropoff_latitude=dropoff_latitude;
	}
	public java.lang.Double getDropoff_latitude(){
		return dropoff_latitude;
	}
	public void setStore_and_fwd_flag(java.lang.String store_and_fwd_flag){
		this.store_and_fwd_flag=store_and_fwd_flag;
	}
	public java.lang.String getStore_and_fwd_flag(){
		return store_and_fwd_flag;
	}
	public void setTrip_duration(java.lang.Integer trip_duration){
		this.trip_duration=trip_duration;
	}
	public java.lang.Integer getTrip_duration(){
		return trip_duration;
	}
	public void setDist_meters(java.lang.Integer dist_meters){
		this.dist_meters=dist_meters;
	}
	public java.lang.Integer getDist_meters(){
		return dist_meters;
	}
	public void setWait_sec(java.lang.Integer wait_sec){
		this.wait_sec=wait_sec;
	}
	public java.lang.Integer getWait_sec(){
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

