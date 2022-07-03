package CollectionXml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectObject {
    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ListPojo list1 = context.getBean("list1", ListPojo.class);
        System.out.println(list1.toString());
    }
}
