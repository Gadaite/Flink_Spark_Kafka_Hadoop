package Package1st;

import Package1st.Config.SpringConfig;
import Package1st.Domain.Account;
import Package1st.Service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring整合mybatis
 */
public class Application2 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        AccountServiceImpl accountService = ctx.getBean(AccountServiceImpl.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        Account ac = accountService.findById(1);
        System.out.println(ac);
    }
}
