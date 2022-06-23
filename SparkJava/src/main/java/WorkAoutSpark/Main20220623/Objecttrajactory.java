package WorkAoutSpark.Main20220623;

import WorkAoutSpark.Main20220622.EntityHelper;

import java.io.Serializable;

/**
 * table name:  Objecttrajactory
 * author name: Gadaite
 * create time: 2022-06-22 19:36:30
 */ 
public class Objecttrajactory extends EntityHelper implements Serializable {

	private Integer lastappeared_id;
	private String gps_line;

	public Objecttrajactory() {
		super();
	}
	public Objecttrajactory(Integer lastappeared_id, String gps_line) {
		this.lastappeared_id=lastappeared_id;
		this.gps_line=gps_line;
	}
	public void setLastappeared_id(Integer lastappeared_id){
		this.lastappeared_id=lastappeared_id;
	}
	public Integer getLastappeared_id(){
		return lastappeared_id;
	}
	public void setGps_line(String gps_line){
		this.gps_line=gps_line;
	}
	public String getGps_line(){
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

