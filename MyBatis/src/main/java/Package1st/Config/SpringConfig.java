package Package1st.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"Package1st"})
@PropertySource(value = "classpath:jdbc.properties")
@Import(value ={JdbcConfig.class,MyBatisConfig.class} )
public class SpringConfig {

}
