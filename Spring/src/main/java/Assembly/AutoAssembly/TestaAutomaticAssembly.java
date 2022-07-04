package Assembly.AutoAssembly;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestaAutomaticAssembly {
    @Test
    public void func1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Emp emp1 = context.getBean("emp1", Emp.class);
        Emp emp2 = context.getBean("emp2", Emp.class);
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
    }
}
