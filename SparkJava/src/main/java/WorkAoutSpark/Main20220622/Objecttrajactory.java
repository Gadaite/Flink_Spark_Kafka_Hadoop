package WorkAoutSpark.Main20220622;

import java.io.Serializable;
/**
 * table name:  Objecttrajactory
 * author name: Gadaite
 * create time: 2022-06-22 19:36:30
 */ 
public class Objecttrajactory extends EntityHelper implements Serializable {

	private java.lang.Integer lastappeared_id;
	private java.lang.String gps_line;

	public Objecttrajactory() {
		super();
	}
	public Objecttrajactory(java.lang.Integer lastappeared_id,java.lang.String gps_line) {
		this.lastappeared_id=lastappeared_id;
		this.gps_line=gps_line;
	}
	public void setLastappeared_id(java.lang.Integer lastappeared_id){
		this.lastappeared_id=lastappeared_id;
	}
	public java.lang.Integer getLastappeared_id(){
		return lastappeared_id;
	}
	public void setGps_line(java.lang.String gps_line){
		this.gps_line=gps_line;
	}
	public java.lang.String getGps_line(){
		return gps_line;
	}
	@Override
	public String toString() {
		return "Objecttrajactory[" + 
			"lastappeared_id=" + lastappeared_id + 
			", gps_line=" + gps_line + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

