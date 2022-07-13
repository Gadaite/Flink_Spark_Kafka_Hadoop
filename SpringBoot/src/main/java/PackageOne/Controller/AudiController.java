package PackageOne.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping(name = "/audi")
public class AudiController {
    @Value("${model}")
    private String model;

    @Value("${year}")
    private Integer year;

    @Value("${price}")
    private Integer price;

    @Value("${transmission}")
    private String transmission;

    @Value("${mileage}")
    private Integer mileage;

    @Value("${fuelType}")
    private String fuelType;

    @Value("${tax}")
    private Integer tax;

    @Value("${mpg}")
    private Double mpg;

    @Value("${engineSize}")
    private Double engineSize;

    @GetMapping(name = "/{year}")
    public void getByYear(@PathVariable Integer year){
        return;
    }

}
