package AnnotationOnly.Test;

import AnnotationOnly.Service.UserService;
import AnnotationOnly.Config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
    @org.junit.Test
    public void func1(){
        //  从配置类中加载
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService1", UserService.class);
        System.out.println(userService);
        userService.test();
    }
}
