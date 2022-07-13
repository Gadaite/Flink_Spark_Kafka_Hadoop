package PackageOne.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audi")
public class Welcome {
    @GetMapping("/{info}")
    public String welcome(@PathVariable String info){
        return "hello " + info;
    }
}
