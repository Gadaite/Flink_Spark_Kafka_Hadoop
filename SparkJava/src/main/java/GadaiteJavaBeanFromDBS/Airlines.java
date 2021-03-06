package GadaiteJavaBeanFromDBS;

import java.io.Serializable;
/**
 * table name:  airlines
 * author name: Gadaite
 * create time: 2022-06-12 00:56:02
 */ 
public class Airlines extends EntityHelper implements Serializable {

	private java.lang.String IATA_CODE;
	private java.lang.String AIRLINE;

	public Airlines() {
		super();
	}
	public Airlines(java.lang.String IATA_CODE,java.lang.String AIRLINE) {
		this.IATA_CODE=IATA_CODE;
		this.AIRLINE=AIRLINE;
	}
	public void setIATA_CODE(java.lang.String IATA_CODE){
		this.IATA_CODE=IATA_CODE;
	}
	public java.lang.String getIATA_CODE(){
		return IATA_CODE;
	}
	public void setAIRLINE(java.lang.String AIRLINE){
		this.AIRLINE=AIRLINE;
	}
	public java.lang.String getAIRLINE(){
		return AIRLINE;
	}
	@Override
	public String toString() {
		return "airlines[" + 
			"IATA_CODE=" + IATA_CODE + 
			", AIRLINE=" + AIRLINE + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

