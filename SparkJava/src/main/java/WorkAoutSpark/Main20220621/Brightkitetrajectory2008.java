package WorkAoutSpark.Main20220621;

import java.io.Serializable;
/**
 * table name:  brightkitetrajectory2008
 * author name: Gadaite
 * create time: 2022-06-21 21:48:57
 */ 
public class Brightkitetrajectory2008 extends EntityHelper implements Serializable {

	private java.lang.Integer user;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.lang.String trajectory;

	public Brightkitetrajectory2008() {
		super();
	}
	public Brightkitetrajectory2008(java.lang.Integer user,java.util.Date startTime,java.util.Date endTime,java.lang.String trajectory) {
		this.user=user;
		this.startTime=startTime;
		this.endTime=endTime;
		this.trajectory=trajectory;
	}
	public void setUser(java.lang.Integer user){
		this.user=user;
	}
	public java.lang.Integer getUser(){
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
	public void setTrajectory(java.lang.String trajectory){
		this.trajectory=trajectory;
	}
	public java.lang.String getTrajectory(){
		return trajectory;
	}
	@Override
	public String toString() {
		return "brightkitetrajectory2008[" + 
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

