package LifeCycle;

import JavaBean.Life;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleInfo {
    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Life cycle1 = context.getBean("cycle1", Life.class);
        System.out.print("获得对象实例");
        System.out.println(cycle1);
        context.close();
    }
}
