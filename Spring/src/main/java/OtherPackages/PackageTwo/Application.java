package OtherPackages.PackageTwo;

import OtherPackages.PackageTwo.Config.SpringConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Application {
    @Test
    public void t1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataSource mysqlDataSource = context.getBean("mysqlDataSource", DataSource.class);
        System.out.println(mysqlDataSource);
    }
}
