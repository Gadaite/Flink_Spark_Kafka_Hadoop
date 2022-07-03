package FactoryBeans;

import CollectionXml.Cource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exec {
    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cource fb1 = context.getBean("fb1", Cource.class);
        System.out.println(fb1.toString());
    }
}
