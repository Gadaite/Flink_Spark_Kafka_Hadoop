package GadaiteJavaBeanFromDBS;

/**
 * table name:  airports
 * author name: Gadaite
 * create time: 2022-05-29 11:47:05
 */ 
public class Airports extends EntityHelper{

	private java.lang.String IATA_CODE;
	private java.lang.String AIRPORT;
	private java.lang.String CITY;
	private java.lang.String STATE;
	private java.lang.String COUNTRY;
	private java.lang.Double LATITUDE;
	private java.lang.Double LONGITUDE;

	public Airports() {
		super();
	}
	public Airports(java.lang.String IATA_CODE,java.lang.String AIRPORT,java.lang.String CITY,java.lang.String STATE,java.lang.String COUNTRY,java.lang.Double LATITUDE,java.lang.Double LONGITUDE) {
		this.IATA_CODE=IATA_CODE;
		this.AIRPORT=AIRPORT;
		this.CITY=CITY;
		this.STATE=STATE;
		this.COUNTRY=COUNTRY;
		this.LATITUDE=LATITUDE;
		this.LONGITUDE=LONGITUDE;
	}
	public void setIATA_CODE(java.lang.String IATA_CODE){
		this.IATA_CODE=IATA_CODE;
	}
	public java.lang.String getIATA_CODE(){
		return IATA_CODE;
	}
	public void setAIRPORT(java.lang.String AIRPORT){
		this.AIRPORT=AIRPORT;
	}
	public java.lang.String getAIRPORT(){
		return AIRPORT;
	}
	public void setCITY(java.lang.String CITY){
		this.CITY=CITY;
	}
	public java.lang.String getCITY(){
		return CITY;
	}
	public void setSTATE(java.lang.String STATE){
		this.STATE=STATE;
	}
	public java.lang.String getSTATE(){
		return STATE;
	}
	public void setCOUNTRY(java.lang.String COUNTRY){
		this.COUNTRY=COUNTRY;
	}
	public java.lang.String getCOUNTRY(){
		return COUNTRY;
	}
	public void setLATITUDE(java.lang.Double LATITUDE){
		this.LATITUDE=LATITUDE;
	}
	public java.lang.Double getLATITUDE(){
		return LATITUDE;
	}
	public void setLONGITUDE(java.lang.Double LONGITUDE){
		this.LONGITUDE=LONGITUDE;
	}
	public java.lang.Double getLONGITUDE(){
		return LONGITUDE;
	}
	@Override
	public String toString() {
		return "airports[" + 
			"IATA_CODE=" + IATA_CODE + 
			", AIRPORT=" + AIRPORT + 
			", CITY=" + CITY + 
			", STATE=" + STATE + 
			", COUNTRY=" + COUNTRY + 
			", LATITUDE=" + LATITUDE + 
			", LONGITUDE=" + LONGITUDE + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

