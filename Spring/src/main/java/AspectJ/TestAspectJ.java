package AspectJ;

import AspectJ.AspectJXml.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectJ {

    @Test
    public void func1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext4.xml");
        User user = context.getBean("user", User.class);
        user.add();
        //  无异常时输出如下
        /**
         * around head ......
         * before ......
         * add ......
         * afterReturning ......
         * after ......
         * around tail ......
         *
         * Process finished with exit code 0
         */
        //  有异常时输出
        /**
         * around head ......
         * before ......
         * afterThrowing ......
         * after ......
         *
         * Process finished with exit code -1
         */
    }
}
