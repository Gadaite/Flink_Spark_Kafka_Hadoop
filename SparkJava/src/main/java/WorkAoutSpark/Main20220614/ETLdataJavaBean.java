package WorkAoutSpark.Main20220614;



import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 待转换表数据的实体类
 */
public class ETLdataJavaBean implements Serializable {
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Timestamp datetime;

    public ETLdataJavaBean(Double latitude, Double longitude, Double altitude, Timestamp datetime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.datetime = datetime;
    }

    public ETLdataJavaBean(){}

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "ETLdataJavaBean{" +
                "Latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", datetime=" + datetime +
                '}';
    }
}
