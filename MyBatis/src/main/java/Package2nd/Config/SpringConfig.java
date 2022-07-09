package Package2nd.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(value = {JdbcConfig.class,MyBatisConfig.class})
@ComponentScan(value = "Package2nd")
//  将事务注解进行生效Transactional
@EnableTransactionManagement
public class SpringConfig {

}
