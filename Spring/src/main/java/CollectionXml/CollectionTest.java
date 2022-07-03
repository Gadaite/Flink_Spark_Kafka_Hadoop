package CollectionXml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class CollectionTest {
    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Stu stu1 = context.getBean("stu1", Stu.class);
        System.out.println(stu1.toString());
    }

}
