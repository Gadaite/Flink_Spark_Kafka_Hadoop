package WorkAoutSpark.Main20220612;

import WorkAoutSpark.Main20220611.EntityHelper;

import java.io.Serializable;

/**
 * table name:  BrightkiteTrajectory2008
 * author name: Gadaite
 * create time: 2022-06-11 18:40:46
 */ 
public class Brightkitetrajectory2008 extends EntityHelper implements Serializable {

	private Integer user;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private String trajectory;

	public Brightkitetrajectory2008() {
		super();
	}
	public Brightkitetrajectory2008(Integer user, java.util.Date startTime, java.util.Date endTime, String trajectory) {
		this.user=user;
		this.startTime=startTime;
		this.endTime=endTime;
		this.trajectory=trajectory;
	}
	public void setUser(Integer user){
		this.user=user;
	}
	public Integer getUser(){
		return user;
	}
	public void setStartTime(java.util.Date startTime){
		this.startTime=startTime;
	}
	public java.util.Date getStartTime(){
		return startTime;
	}
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
	}
	public java.util.Date getEndTime(){
		return endTime;
	}
	public void setTrajectory(String trajectory){
		this.trajectory=trajectory;
	}
	public String getTrajectory(){
		return trajectory;
	}
	@Override
	public String toString() {
		return "BrightkiteTrajectory2008[" + 
			"user=" + user + 
			", startTime=" + startTime + 
			", endTime=" + endTime + 
			", trajectory=" + trajectory + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

