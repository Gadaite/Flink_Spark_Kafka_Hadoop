package WorkAoutSpark.Main20220529;

/**
 * table name:  BIXIMontrealOD2014
 * author name: Gadaite
 * create time: 2022-05-29 23:21:31
 */ 
public class Biximontrealod2014 extends EntityHelper{

	private java.lang.Integer Column1;
	private java.lang.String start_date;
	private java.lang.Integer start_station_code;
	private java.lang.String end_date;
	private java.lang.Integer end_station_code;
	private java.lang.Integer duration_sec;
	private java.lang.Integer is_member;

	public Biximontrealod2014() {
		super();
	}
	public Biximontrealod2014(java.lang.Integer Column1,java.lang.String start_date,java.lang.Integer start_station_code,java.lang.String end_date,java.lang.Integer end_station_code,java.lang.Integer duration_sec,java.lang.Integer is_member) {
		this.Column1=Column1;
		this.start_date=start_date;
		this.start_station_code=start_station_code;
		this.end_date=end_date;
		this.end_station_code=end_station_code;
		this.duration_sec=duration_sec;
		this.is_member=is_member;
	}
	public void setColumn1(java.lang.Integer Column1){
		this.Column1=Column1;
	}
	public java.lang.Integer getColumn1(){
		return Column1;
	}
	public void setStart_date(java.lang.String start_date){
		this.start_date=start_date;
	}
	public java.lang.String getStart_date(){
		return start_date;
	}
	public void setStart_station_code(java.lang.Integer start_station_code){
		this.start_station_code=start_station_code;
	}
	public java.lang.Integer getStart_station_code(){
		return start_station_code;
	}
	public void setEnd_date(java.lang.String end_date){
		this.end_date=end_date;
	}
	public java.lang.String getEnd_date(){
		return end_date;
	}
	public void setEnd_station_code(java.lang.Integer end_station_code){
		this.end_station_code=end_station_code;
	}
	public java.lang.Integer getEnd_station_code(){
		return end_station_code;
	}
	public void setDuration_sec(java.lang.Integer duration_sec){
		this.duration_sec=duration_sec;
	}
	public java.lang.Integer getDuration_sec(){
		return duration_sec;
	}
	public void setIs_member(java.lang.Integer is_member){
		this.is_member=is_member;
	}
	public java.lang.Integer getIs_member(){
		return is_member;
	}
	@Override
	public String toString() {
		return "BIXIMontrealOD2014[" + 
			"Column1=" + Column1 + 
			", start_date=" + start_date + 
			", start_station_code=" + start_station_code + 
			", end_date=" + end_date + 
			", end_station_code=" + end_station_code + 
			", duration_sec=" + duration_sec + 
			", is_member=" + is_member + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

