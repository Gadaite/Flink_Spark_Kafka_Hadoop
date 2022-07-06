package AnnotationOnly.Service;

import AnnotationOnly.Dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Component(value = "userService")
//@Controller(value = "userService")
//@Repository(value = "userService")
@Service(value = "userService1")
public class UserService {
    //  先定义Dao的属性,不需要添加Set方法

//    M1:属性注入方式
//    @Autowired //   根据类型进行注入


//    M2:名称注入方式
//    @Autowired //   根据类型进行注入
//    @Qualifier(value ="userDaoImpl") //   根据名称进行注入，需要和Autowired一起使用


//    M3:既可以类型注入，也可以名称注入,带参数就是名称注入，不带参数就是属性注入
//    @Resource
    @Resource(name = "userDaoImpl1")
    private UserDao userDao;
    //  Value只能注入普通类型属性
    @Value(value = "Gadaite")
    private String name;
    public void test(){
        System.out.println("Service without xml");
        userDao.dao();
        System.out.println(name);
    }
}
