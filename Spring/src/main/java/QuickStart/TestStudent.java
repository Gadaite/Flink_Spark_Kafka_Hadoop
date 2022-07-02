package QuickStart;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    public static void main(String[] args) {
        //  创建IOC容器对象
        ClassPathXmlApplicationContext demo = new ClassPathXmlApplicationContext("applicationContext.xml");
        //  根据id值获取bean实例对象
        Student student = demo.getBean("student", Student.class);
        student.setName("Gadaite");
        student.setAge(24);
        student.setSex("male");
        System.out.println(student.toString());
        Student student2 = demo.getBean("student2", Student.class);
        System.out.println(student2.toString());
        Student student3 = demo.getBean("student3", Student.class);
        System.out.println(student3.toString());
        Student student4 = demo.getBean("student4", Student.class);
        System.out.println(student4.toString());

    }
}
