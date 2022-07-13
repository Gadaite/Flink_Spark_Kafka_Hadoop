package PackageOne.Controller;

import PackageOne.Entity.AuthorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取配置文件中的内容的两种方式
 */
@RestController
@RequestMapping(value = "/authorInfo")
public class AuthorInfoController {

//      方式1
    @Value(value = "${authorInfo.name}")
    private String name;

    @Value(value = "${authorInfo.age}")
    private int age;

    @Value(value = "${authorInfo.tel}")
    private String tel;

    @Value(value = "${authorInfo.mail}")
    private String mail;

    @GetMapping
    public AuthorInfo getMyInfo() {
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setName(name);
        authorInfo.setAge(age);
        authorInfo.setTel(tel);
        authorInfo.setMail(mail);
        return authorInfo;
    }

//      方式2

    @Autowired
    private Environment env;

    @GetMapping(value = "/{info}")
    public String getInfo(@PathVariable String info){
        String information = env.getProperty("authorInfo."+info);
        return information;
    }


}
