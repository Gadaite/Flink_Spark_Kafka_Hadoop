package ObjectType;

import CollectionXml.Cource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExecClass {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    //  判断是1单例11对象还是多例对象
    @Test
    public void func1(){
        Cource cource1 = context.getBean("cource1", Cource.class);
        Cource cource2 = context.getBean("cource1", Cource.class);
        System.out.println(System.identityHashCode(cource1));
        System.out.println(System.identityHashCode(cource2));
    }
    @Test
    public void func2(){
        Cource cource1 = context.getBean("a1", Cource.class);
        Cource cource2 = context.getBean("a1", Cource.class);
        System.out.println(System.identityHashCode(cource1));
        System.out.println(System.identityHashCode(cource2));
    }
}
