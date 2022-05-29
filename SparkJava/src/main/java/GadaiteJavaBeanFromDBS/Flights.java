package GadaiteJavaBeanFromDBS;

/**
 * table name:  flights
 * author name: Gadaite
 * create time: 2022-05-29 11:53:24
 */ 
public class Flights extends EntityHelper{

	private java.lang.Integer YEAR;
	private java.lang.Integer MONTH;
	private java.lang.Integer DAY;
	private java.lang.Integer DAY_OF_WEEK;
	private java.lang.String AIRLINE;
	private java.lang.Integer FLIGHT_NUMBER;
	private java.lang.String TAIL_NUMBER;
	private java.lang.String ORIGIN_AIRPORT;
	private java.lang.String DESTINATION_AIRPORT;
	private java.lang.Integer SCHEDULED_DEPARTURE;
	private java.lang.Integer DEPARTURE_TIME;
	private java.lang.Integer DEPARTURE_DELAY;
	private java.lang.Integer TAXI_OUT;
	private java.lang.Integer WHEELS_OFF;
	private java.lang.Integer SCHEDULED_TIME;
	private java.lang.Integer ELAPSED_TIME;
	private java.lang.Integer AIR_TIME;
	private java.lang.Integer DISTANCE;
	private java.lang.Integer WHEELS_ON;
	private java.lang.Integer TAXI_IN;
	private java.lang.Integer SCHEDULED_ARRIVAL;
	private java.lang.Integer ARRIVAL_TIME;
	private java.lang.Integer ARRIVAL_DELAY;
	private java.lang.Integer DIVERTED;
	private java.lang.Integer CANCELLED;
	private java.lang.String CANCELLATION_REASON;
	private java.lang.Integer AIR_SYSTEM_DELAY;
	private java.lang.Integer SECURITY_DELAY;
	private java.lang.Integer AIRLINE_DELAY;
	private java.lang.Integer LATE_AIRCRAFT_DELAY;
	private java.lang.Integer WEATHER_DELAY;

	public Flights() {
		super();
	}
	public Flights(java.lang.Integer YEAR,java.lang.Integer MONTH,java.lang.Integer DAY,java.lang.Integer DAY_OF_WEEK,java.lang.String AIRLINE,java.lang.Integer FLIGHT_NUMBER,java.lang.String TAIL_NUMBER,java.lang.String ORIGIN_AIRPORT,java.lang.String DESTINATION_AIRPORT,java.lang.Integer SCHEDULED_DEPARTURE,java.lang.Integer DEPARTURE_TIME,java.lang.Integer DEPARTURE_DELAY,java.lang.Integer TAXI_OUT,java.lang.Integer WHEELS_OFF,java.lang.Integer SCHEDULED_TIME,java.lang.Integer ELAPSED_TIME,java.lang.Integer AIR_TIME,java.lang.Integer DISTANCE,java.lang.Integer WHEELS_ON,java.lang.Integer TAXI_IN,java.lang.Integer SCHEDULED_ARRIVAL,java.lang.Integer ARRIVAL_TIME,java.lang.Integer ARRIVAL_DELAY,java.lang.Integer DIVERTED,java.lang.Integer CANCELLED,java.lang.String CANCELLATION_REASON,java.lang.Integer AIR_SYSTEM_DELAY,java.lang.Integer SECURITY_DELAY,java.lang.Integer AIRLINE_DELAY,java.lang.Integer LATE_AIRCRAFT_DELAY,java.lang.Integer WEATHER_DELAY) {
		this.YEAR=YEAR;
		this.MONTH=MONTH;
		this.DAY=DAY;
		this.DAY_OF_WEEK=DAY_OF_WEEK;
		this.AIRLINE=AIRLINE;
		this.FLIGHT_NUMBER=FLIGHT_NUMBER;
		this.TAIL_NUMBER=TAIL_NUMBER;
		this.ORIGIN_AIRPORT=ORIGIN_AIRPORT;
		this.DESTINATION_AIRPORT=DESTINATION_AIRPORT;
		this.SCHEDULED_DEPARTURE=SCHEDULED_DEPARTURE;
		this.DEPARTURE_TIME=DEPARTURE_TIME;
		this.DEPARTURE_DELAY=DEPARTURE_DELAY;
		this.TAXI_OUT=TAXI_OUT;
		this.WHEELS_OFF=WHEELS_OFF;
		this.SCHEDULED_TIME=SCHEDULED_TIME;
		this.ELAPSED_TIME=ELAPSED_TIME;
		this.AIR_TIME=AIR_TIME;
		this.DISTANCE=DISTANCE;
		this.WHEELS_ON=WHEELS_ON;
		this.TAXI_IN=TAXI_IN;
		this.SCHEDULED_ARRIVAL=SCHEDULED_ARRIVAL;
		this.ARRIVAL_TIME=ARRIVAL_TIME;
		this.ARRIVAL_DELAY=ARRIVAL_DELAY;
		this.DIVERTED=DIVERTED;
		this.CANCELLED=CANCELLED;
		this.CANCELLATION_REASON=CANCELLATION_REASON;
		this.AIR_SYSTEM_DELAY=AIR_SYSTEM_DELAY;
		this.SECURITY_DELAY=SECURITY_DELAY;
		this.AIRLINE_DELAY=AIRLINE_DELAY;
		this.LATE_AIRCRAFT_DELAY=LATE_AIRCRAFT_DELAY;
		this.WEATHER_DELAY=WEATHER_DELAY;
	}
	public void setYEAR(java.lang.Integer YEAR){
		this.YEAR=YEAR;
	}
	public java.lang.Integer getYEAR(){
		return YEAR;
	}
	public void setMONTH(java.lang.Integer MONTH){
		this.MONTH=MONTH;
	}
	public java.lang.Integer getMONTH(){
		return MONTH;
	}
	public void setDAY(java.lang.Integer DAY){
		this.DAY=DAY;
	}
	public java.lang.Integer getDAY(){
		return DAY;
	}
	public void setDAY_OF_WEEK(java.lang.Integer DAY_OF_WEEK){
		this.DAY_OF_WEEK=DAY_OF_WEEK;
	}
	public java.lang.Integer getDAY_OF_WEEK(){
		return DAY_OF_WEEK;
	}
	public void setAIRLINE(java.lang.String AIRLINE){
		this.AIRLINE=AIRLINE;
	}
	public java.lang.String getAIRLINE(){
		return AIRLINE;
	}
	public void setFLIGHT_NUMBER(java.lang.Integer FLIGHT_NUMBER){
		this.FLIGHT_NUMBER=FLIGHT_NUMBER;
	}
	public java.lang.Integer getFLIGHT_NUMBER(){
		return FLIGHT_NUMBER;
	}
	public void setTAIL_NUMBER(java.lang.String TAIL_NUMBER){
		this.TAIL_NUMBER=TAIL_NUMBER;
	}
	public java.lang.String getTAIL_NUMBER(){
		return TAIL_NUMBER;
	}
	public void setORIGIN_AIRPORT(java.lang.String ORIGIN_AIRPORT){
		this.ORIGIN_AIRPORT=ORIGIN_AIRPORT;
	}
	public java.lang.String getORIGIN_AIRPORT(){
		return ORIGIN_AIRPORT;
	}
	public void setDESTINATION_AIRPORT(java.lang.String DESTINATION_AIRPORT){
		this.DESTINATION_AIRPORT=DESTINATION_AIRPORT;
	}
	public java.lang.String getDESTINATION_AIRPORT(){
		return DESTINATION_AIRPORT;
	}
	public void setSCHEDULED_DEPARTURE(java.lang.Integer SCHEDULED_DEPARTURE){
		this.SCHEDULED_DEPARTURE=SCHEDULED_DEPARTURE;
	}
	public java.lang.Integer getSCHEDULED_DEPARTURE(){
		return SCHEDULED_DEPARTURE;
	}
	public void setDEPARTURE_TIME(java.lang.Integer DEPARTURE_TIME){
		this.DEPARTURE_TIME=DEPARTURE_TIME;
	}
	public java.lang.Integer getDEPARTURE_TIME(){
		return DEPARTURE_TIME;
	}
	public void setDEPARTURE_DELAY(java.lang.Integer DEPARTURE_DELAY){
		this.DEPARTURE_DELAY=DEPARTURE_DELAY;
	}
	public java.lang.Integer getDEPARTURE_DELAY(){
		return DEPARTURE_DELAY;
	}
	public void setTAXI_OUT(java.lang.Integer TAXI_OUT){
		this.TAXI_OUT=TAXI_OUT;
	}
	public java.lang.Integer getTAXI_OUT(){
		return TAXI_OUT;
	}
	public void setWHEELS_OFF(java.lang.Integer WHEELS_OFF){
		this.WHEELS_OFF=WHEELS_OFF;
	}
	public java.lang.Integer getWHEELS_OFF(){
		return WHEELS_OFF;
	}
	public void setSCHEDULED_TIME(java.lang.Integer SCHEDULED_TIME){
		this.SCHEDULED_TIME=SCHEDULED_TIME;
	}
	public java.lang.Integer getSCHEDULED_TIME(){
		return SCHEDULED_TIME;
	}
	public void setELAPSED_TIME(java.lang.Integer ELAPSED_TIME){
		this.ELAPSED_TIME=ELAPSED_TIME;
	}
	public java.lang.Integer getELAPSED_TIME(){
		return ELAPSED_TIME;
	}
	public void setAIR_TIME(java.lang.Integer AIR_TIME){
		this.AIR_TIME=AIR_TIME;
	}
	public java.lang.Integer getAIR_TIME(){
		return AIR_TIME;
	}
	public void setDISTANCE(java.lang.Integer DISTANCE){
		this.DISTANCE=DISTANCE;
	}
	public java.lang.Integer getDISTANCE(){
		return DISTANCE;
	}
	public void setWHEELS_ON(java.lang.Integer WHEELS_ON){
		this.WHEELS_ON=WHEELS_ON;
	}
	public java.lang.Integer getWHEELS_ON(){
		return WHEELS_ON;
	}
	public void setTAXI_IN(java.lang.Integer TAXI_IN){
		this.TAXI_IN=TAXI_IN;
	}
	public java.lang.Integer getTAXI_IN(){
		return TAXI_IN;
	}
	public void setSCHEDULED_ARRIVAL(java.lang.Integer SCHEDULED_ARRIVAL){
		this.SCHEDULED_ARRIVAL=SCHEDULED_ARRIVAL;
	}
	public java.lang.Integer getSCHEDULED_ARRIVAL(){
		return SCHEDULED_ARRIVAL;
	}
	public void setARRIVAL_TIME(java.lang.Integer ARRIVAL_TIME){
		this.ARRIVAL_TIME=ARRIVAL_TIME;
	}
	public java.lang.Integer getARRIVAL_TIME(){
		return ARRIVAL_TIME;
	}
	public void setARRIVAL_DELAY(java.lang.Integer ARRIVAL_DELAY){
		this.ARRIVAL_DELAY=ARRIVAL_DELAY;
	}
	public java.lang.Integer getARRIVAL_DELAY(){
		return ARRIVAL_DELAY;
	}
	public void setDIVERTED(java.lang.Integer DIVERTED){
		this.DIVERTED=DIVERTED;
	}
	public java.lang.Integer getDIVERTED(){
		return DIVERTED;
	}
	public void setCANCELLED(java.lang.Integer CANCELLED){
		this.CANCELLED=CANCELLED;
	}
	public java.lang.Integer getCANCELLED(){
		return CANCELLED;
	}
	public void setCANCELLATION_REASON(java.lang.String CANCELLATION_REASON){
		this.CANCELLATION_REASON=CANCELLATION_REASON;
	}
	public java.lang.String getCANCELLATION_REASON(){
		return CANCELLATION_REASON;
	}
	public void setAIR_SYSTEM_DELAY(java.lang.Integer AIR_SYSTEM_DELAY){
		this.AIR_SYSTEM_DELAY=AIR_SYSTEM_DELAY;
	}
	public java.lang.Integer getAIR_SYSTEM_DELAY(){
		return AIR_SYSTEM_DELAY;
	}
	public void setSECURITY_DELAY(java.lang.Integer SECURITY_DELAY){
		this.SECURITY_DELAY=SECURITY_DELAY;
	}
	public java.lang.Integer getSECURITY_DELAY(){
		return SECURITY_DELAY;
	}
	public void setAIRLINE_DELAY(java.lang.Integer AIRLINE_DELAY){
		this.AIRLINE_DELAY=AIRLINE_DELAY;
	}
	public java.lang.Integer getAIRLINE_DELAY(){
		return AIRLINE_DELAY;
	}
	public void setLATE_AIRCRAFT_DELAY(java.lang.Integer LATE_AIRCRAFT_DELAY){
		this.LATE_AIRCRAFT_DELAY=LATE_AIRCRAFT_DELAY;
	}
	public java.lang.Integer getLATE_AIRCRAFT_DELAY(){
		return LATE_AIRCRAFT_DELAY;
	}
	public void setWEATHER_DELAY(java.lang.Integer WEATHER_DELAY){
		this.WEATHER_DELAY=WEATHER_DELAY;
	}
	public java.lang.Integer getWEATHER_DELAY(){
		return WEATHER_DELAY;
	}
	@Override
	public String toString() {
		return "flights[" + 
			"YEAR=" + YEAR + 
			", MONTH=" + MONTH + 
			", DAY=" + DAY + 
			", DAY_OF_WEEK=" + DAY_OF_WEEK + 
			", AIRLINE=" + AIRLINE + 
			", FLIGHT_NUMBER=" + FLIGHT_NUMBER + 
			", TAIL_NUMBER=" + TAIL_NUMBER + 
			", ORIGIN_AIRPORT=" + ORIGIN_AIRPORT + 
			", DESTINATION_AIRPORT=" + DESTINATION_AIRPORT + 
			", SCHEDULED_DEPARTURE=" + SCHEDULED_DEPARTURE + 
			", DEPARTURE_TIME=" + DEPARTURE_TIME + 
			", DEPARTURE_DELAY=" + DEPARTURE_DELAY + 
			", TAXI_OUT=" + TAXI_OUT + 
			", WHEELS_OFF=" + WHEELS_OFF + 
			", SCHEDULED_TIME=" + SCHEDULED_TIME + 
			", ELAPSED_TIME=" + ELAPSED_TIME + 
			", AIR_TIME=" + AIR_TIME + 
			", DISTANCE=" + DISTANCE + 
			", WHEELS_ON=" + WHEELS_ON + 
			", TAXI_IN=" + TAXI_IN + 
			", SCHEDULED_ARRIVAL=" + SCHEDULED_ARRIVAL + 
			", ARRIVAL_TIME=" + ARRIVAL_TIME + 
			", ARRIVAL_DELAY=" + ARRIVAL_DELAY + 
			", DIVERTED=" + DIVERTED + 
			", CANCELLED=" + CANCELLED + 
			", CANCELLATION_REASON=" + CANCELLATION_REASON + 
			", AIR_SYSTEM_DELAY=" + AIR_SYSTEM_DELAY + 
			", SECURITY_DELAY=" + SECURITY_DELAY + 
			", AIRLINE_DELAY=" + AIRLINE_DELAY + 
			", LATE_AIRCRAFT_DELAY=" + LATE_AIRCRAFT_DELAY + 
			", WEATHER_DELAY=" + WEATHER_DELAY + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

