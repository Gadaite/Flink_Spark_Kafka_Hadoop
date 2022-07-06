package AspectJ.AspectJXml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * User的增强类
 */
@Component(value = "userProxy")
@Aspect //生成代理对象
public class UserProxy {

    //  前置通知
    @Before(value = "execution(* AspectJ.AspectJXml.User.add(..))")
    public void before(){
        System.out.println("before ......");
    }

    //  最终通知
    @After(value = "execution(* AspectJ.AspectJXml.User.add(..))")
    public void after(){
        System.out.println("after ......");
    }

    //  后置通知(返回通知)
    @AfterReturning(value = "execution(* AspectJ.AspectJXml.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning ......");
    }

    //  异常通知,被增强方法有异常才执行
    @AfterThrowing(value = "execution(* AspectJ.AspectJXml.User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing ......");
    }

    //  环绕通知
    @Around(value = "execution(* AspectJ.AspectJXml.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around head ......");
        proceedingJoinPoint.proceed();
        System.out.println("around tail ......");
    }
}
