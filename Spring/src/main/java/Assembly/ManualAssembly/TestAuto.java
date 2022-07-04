package Assembly.ManualAssembly;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAuto {
    @Test
    public void func1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Emp emp4 = context.getBean("emp4", Emp.class);
        System.out.println(emp4.toString());
    }
}
