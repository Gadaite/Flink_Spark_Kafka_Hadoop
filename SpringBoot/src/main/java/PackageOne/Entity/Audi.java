package PackageOne.Entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
//@ConfigurationProperties(prefix = "")
public class Audi implements Serializable {
    private String model;
    private Integer year;
    private Integer price;
    private String transmission;
    private Integer mileage;
    private String fuelType;
    private Integer tax;
    private Double mpg;
    private Double engineSize;
}
