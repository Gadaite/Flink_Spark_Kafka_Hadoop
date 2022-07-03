package FactoryBeans;

import CollectionXml.Cource;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Cource> {
    //  定义返回对象1类型
    public Cource getObject() throws Exception {
        Cource cource = new Cource();
        cource.setCource("Spring");
        return cource;
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }
}
