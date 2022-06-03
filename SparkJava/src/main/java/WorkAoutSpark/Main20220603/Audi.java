package WorkAoutSpark.Main20220603;

import java.io.Serializable;

/**
 * table name:  audi
 * author name: Gadaite
 * create time: 2022-06-03 17:26:55
 */ 
public class Audi extends EntityHelper implements Serializable {

	private java.lang.String model;
	private java.lang.Integer year;
	private java.lang.Integer price;
	private java.lang.String transmission;
	private java.lang.Integer mileage;
	private java.lang.String fuelType;
	private java.lang.Integer tax;
	private java.lang.Double mpg;
	private java.lang.Double engineSize;

	public Audi() {
		super();
	}
	public Audi(java.lang.String model,java.lang.Integer year,java.lang.Integer price,java.lang.String transmission,java.lang.Integer mileage,java.lang.String fuelType,java.lang.Integer tax,java.lang.Double mpg,java.lang.Double engineSize) {
		this.model=model;
		this.year=year;
		this.price=price;
		this.transmission=transmission;
		this.mileage=mileage;
		this.fuelType=fuelType;
		this.tax=tax;
		this.mpg=mpg;
		this.engineSize=engineSize;
	}
	public void setModel(java.lang.String model){
		this.model=model;
	}
	public java.lang.String getModel(){
		return model;
	}
	public void setYear(java.lang.Integer year){
		this.year=year;
	}
	public java.lang.Integer getYear(){
		return year;
	}
	public void setPrice(java.lang.Integer price){
		this.price=price;
	}
	public java.lang.Integer getPrice(){
		return price;
	}
	public void setTransmission(java.lang.String transmission){
		this.transmission=transmission;
	}
	public java.lang.String getTransmission(){
		return transmission;
	}
	public void setMileage(java.lang.Integer mileage){
		this.mileage=mileage;
	}
	public java.lang.Integer getMileage(){
		return mileage;
	}
	public void setFuelType(java.lang.String fuelType){
		this.fuelType=fuelType;
	}
	public java.lang.String getFuelType(){
		return fuelType;
	}
	public void setTax(java.lang.Integer tax){
		this.tax=tax;
	}
	public java.lang.Integer getTax(){
		return tax;
	}
	public void setMpg(java.lang.Double mpg){
		this.mpg=mpg;
	}
	public java.lang.Double getMpg(){
		return mpg;
	}
	public void setEngineSize(java.lang.Double engineSize){
		this.engineSize=engineSize;
	}
	public java.lang.Double getEngineSize(){
		return engineSize;
	}
	@Override
	public String toString() {
		return "audi[" + 
			"model=" + model + 
			", year=" + year + 
			", price=" + price + 
			", transmission=" + transmission + 
			", mileage=" + mileage + 
			", fuelType=" + fuelType + 
			", tax=" + tax + 
			", mpg=" + mpg + 
			", engineSize=" + engineSize + 
			"]";
	}
	@Override
	public String getPrimaryKey() {
		return "null";
	}
}

