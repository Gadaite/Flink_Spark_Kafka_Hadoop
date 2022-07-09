package OtherPackages.PackageOne.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {JdbcConfig.class})
public class SpringConfig {
}
