package WorkAoutSpark.Main20220604;

import WorkAoutSpark.Main20220603.EntityHelper;

import java.io.Serializable;

/**
 * table name:  audi
 * author name: Gadaite
 * create time: 2022-06-03 17:26:55
 */ 
public class Audi extends EntityHelper implements Serializable,Comparable<Audi> {

	private String model;
	private Integer year;
	private Integer price;
	private String transmission;
	private Integer mileage;
	private String fuelType;
	private Integer tax;
	private Double mpg;
	private Double engineSize;

	public Audi() {
		super();
	}
	public Audi(String model, Integer year, Integer price, String transmission, Integer mileage, String fuelType, Integer tax, Double mpg, Double engineSize) {
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
	public void setModel(String model){
		this.model=model;
	}
	public String getModel(){
		return model;
	}
	public void setYear(Integer year){
		this.year=year;
	}
	public Integer getYear(){
		return year;
	}
	public void setPrice(Integer price){
		this.price=price;
	}
	public Integer getPrice(){
		return price;
	}
	public void setTransmission(String transmission){
		this.transmission=transmission;
	}
	public String getTransmission(){
		return transmission;
	}
	public void setMileage(Integer mileage){
		this.mileage=mileage;
	}
	public Integer getMileage(){
		return mileage;
	}
	public void setFuelType(String fuelType){
		this.fuelType=fuelType;
	}
	public String getFuelType(){
		return fuelType;
	}
	public void setTax(Integer tax){
		this.tax=tax;
	}
	public Integer getTax(){
		return tax;
	}
	public void setMpg(Double mpg){
		this.mpg=mpg;
	}
	public Double getMpg(){
		return mpg;
	}
	public void setEngineSize(Double engineSize){
		this.engineSize=engineSize;
	}
	public Double getEngineSize(){
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

	@Override
	public int compareTo(Audi o) {
		return this.year.compareTo(o.getYear());
	}
}

