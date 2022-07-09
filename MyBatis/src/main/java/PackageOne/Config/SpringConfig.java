package PackageOne.Config;

import org.apache.ibatis.javassist.ClassPath;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"PackageOne"})
@PropertySource(value = "classpath:jdbc.properties")
@Import(value ={JdbcConfig.class,MyBatisConfig.class} )
public class SpringConfig {

}
