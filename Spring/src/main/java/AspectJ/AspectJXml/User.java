package AspectJ.AspectJXml;

import org.springframework.stereotype.Component;

@Component(value = "user")
public class User {
    public void add(){
        // 模拟一个异常
//        int i = 10/0;
        System.out.println("add ......");
    }
}
