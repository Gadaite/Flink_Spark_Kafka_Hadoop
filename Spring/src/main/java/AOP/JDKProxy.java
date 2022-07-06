package AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.UUID;

public class JDKProxy {
    public static void main(String[] args) {

        //  创建接口实现类的代理对象
        Class[] inter = {InterfaceImpl1.class};
        InterfaceImpl1 interfaceImpl1 = new InterfaceImpl1();
        InterfaceImpl1 res =
                (InterfaceImpl1) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), inter, new proxy(interfaceImpl1));
        System.out.println(res.add(111, 222));
        System.out.println(res.update(UUID.randomUUID().toString()));

    }

    public static class proxy implements InvocationHandler{
        //  传递被代理的对象
        private Object object;
        public proxy(Object object){
            this.object = object;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //  方法之前执行
            System.out.println(method.getName());
            System.out.println(Arrays.toString(args));
            //  被增强方法执行
            Object o = method.invoke(object, args);
            //  方法之后执行
            System.out.println(object);
            return o;
        }
    }
}
