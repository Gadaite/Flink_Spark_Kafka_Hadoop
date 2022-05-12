package CETC10S.Main20220512;

public class AudiModel {
    private String model;
    private int year;
    private int price;
    private String transmission;
    private int mileage;
    private String fuelType;
    private int tax;
    private Double mpg;
    private Double engineSize;
    public AudiModel(){}
    public AudiModel(String model, int year, int price, String transmission, int mileage, String fuelType, int tax, Double mpg, Double engineSize) {
        this.model = model;
        this.year = year;
        this.price = price;
        this.transmission = transmission;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.tax = tax;
        this.mpg = mpg;
        this.engineSize = engineSize;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public Double getMpg() {
        return mpg;
    }

    public void setMpg(Double mpg) {
        this.mpg = mpg;
    }

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }
}
