package LifeCycle;

import JavaBean.Life;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 在初始化之后执行的方法(配置文件中的所有id)
 * 进入无参构造函数
 * 进入set方法注入值
 * 在初始化之前执行的方法
 * 进入初始化函数
 * 在初始化之后执行的方法(只限于当前的id的对象)
 * 获得对象实例JavaBean.Life@31206beb
 * 进入销毁函数
 */
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
