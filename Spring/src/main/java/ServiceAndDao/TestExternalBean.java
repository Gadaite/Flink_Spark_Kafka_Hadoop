package ServiceAndDao;

import ServiceAndDao.Service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestExternalBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.welcome();
    }
}
