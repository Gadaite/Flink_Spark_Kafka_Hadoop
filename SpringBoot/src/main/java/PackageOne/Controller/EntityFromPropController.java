package PackageOne.Controller;

import PackageOne.Entity.EntityFromProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testEntityFromProp")
public class EntityFromPropController {
    @Autowired
    private EntityFromProp entityFromProp;
    @GetMapping
    public String testEntityFromProp(){
        return entityFromProp.getInfo();
    }
}
