package OtherPackages.PackageTwo.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {JdbcConfig.class})
@ComponentScan(basePackages = {"OtherPackages.PackageTwo.Dao"})
public class SpringConfig {
}
