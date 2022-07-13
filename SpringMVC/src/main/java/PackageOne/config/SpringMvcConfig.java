package PackageOne.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "PackageOne.controller")
public class SpringMvcConfig {
}
