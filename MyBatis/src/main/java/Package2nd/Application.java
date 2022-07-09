package Package2nd;

import Package2nd.Config.SpringConfig;
import Package2nd.Service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring事务管理
 */
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService bean = context.getBean(AccountService.class);

        System.out.println("before -------------------");
        bean.selectMoney("Gadaite").forEach(x-> System.out.println(x));
        bean.selectMoney("xiaobao").forEach(x-> System.out.println(x));

        System.out.println("After --------------------");
        bean.transfer("Gadaite","xiaobao",600D);
        bean.selectMoney("Gadaite").forEach(x-> System.out.println(x));
        bean.selectMoney("xiaobao").forEach(x-> System.out.println(x));

        bean.transfer("xiaobao","Gadaite",600D);

    }
}
