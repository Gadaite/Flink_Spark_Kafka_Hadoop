package WorkAoutSpark.Main20220602;

public class AudiAvgPrice {
    private String model;
    private Integer year;
    private Double avg_price;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(Double avg_price) {
        this.avg_price = avg_price;
    }

    public AudiAvgPrice(String model, Integer year, Double avg_price) {
        this.model = model;
        this.year = year;
        this.avg_price = avg_price;
    }
}
