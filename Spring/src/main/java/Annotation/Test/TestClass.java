package Annotation.Test;

import Annotation.Service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClass {
    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.test();
    }
}
