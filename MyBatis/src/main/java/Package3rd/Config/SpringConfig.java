package Package3rd.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(value = {MyBatisConfig.class,JdbcConfig.class})
@EnableTransactionManagement
@ComponentScan(basePackages = "Package3rd")
public class SpringConfig {
}
