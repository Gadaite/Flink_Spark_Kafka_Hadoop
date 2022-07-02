package Literal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LiteralSetNull {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //  int 类型不能设置空值
        //  integer类型可以设置空值
        Student student1 = context.getBean("student1", Student.class);
        System.out.println(student1.toString());
        Student student2 = context.getBean("student5", Student.class);
        System.out.println(student2.toString());
    }
}
