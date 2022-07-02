package Literal;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LiteralSetSpecialSymbols {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //  使用CDATA处理特殊符号
        Student student6 = context.getBean("student6", Student.class);
        System.out.println(student6.toString());
        //  使用转义字符处理特殊符号
        Student student7 = context.getBean("student7", Student.class);
        System.out.println(student7.toString());
    }
}
