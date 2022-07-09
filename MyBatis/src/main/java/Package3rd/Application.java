package Package3rd;

import Package3rd.Service.AccountService;
import Package3rd.Config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService service = context.getBean(AccountService.class);
        service.transfer("Gadaite","xiaobao",-100D);
    }
}
