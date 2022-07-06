package AnnotationOnly.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//  创建配置类，注册配置类
@Configuration
@ComponentScan(basePackages = {"AnnotationOnly"})
public class SpringConfig {

}
