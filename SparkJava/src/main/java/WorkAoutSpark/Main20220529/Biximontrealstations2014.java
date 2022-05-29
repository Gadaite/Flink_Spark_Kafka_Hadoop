package WorkAoutSpark.Main20220529;

/**
 * table name:  BIXIMontrealStations2014
 * author name: Gadaite
 * create time: 2022-05-29 21:16:15
 */ 
public class Biximontrealstations2014 extends EntityHelper{

	private java.lang.Integer code;
	private java.lang.String name;
	private java.lang.Double latitude;
	private java.lang.Double longitude;

	public Biximontrealstations2014() {
		super();
	}
	public Biximontrealstations2014(java.lang.Integer code,java.lang.String name,java.lang.Double latitude,java.lang.Double longitude) {
		this.code=code;
		this.name=name;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	public void setCode(java.lang.Integer code){
		this.code=code;
	}
	public java.lang.Integer getCode(){
		return code;
	}
	public void setName(java.lang.String name){
		this.name=name;
	}
	public java.lang.String getName(){
		return name;
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
	@Override
	public String toString() {
		return "BIXIMontrealStations2014[" + 
			"code=" + code + 
			", name=" + name + 
			", latitude=" + latitude + 
			", longitude=" + longitude + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

