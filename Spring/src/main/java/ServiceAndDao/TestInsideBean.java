package ServiceAndDao;

import JavaBean.Emp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInsideBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Emp emp = context.getBean("emp", Emp.class);
        Emp emp2 = context.getBean("emp2", Emp.class);
        Emp emp3 = context.getBean("emp3", Emp.class);
        System.out.println(emp.toString());
        System.out.println(emp2.toString());
        System.out.println(emp3.toString());
    }
}
