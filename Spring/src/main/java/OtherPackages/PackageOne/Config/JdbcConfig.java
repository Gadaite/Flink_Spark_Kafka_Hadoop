package OtherPackages.PackageOne.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig {

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.1.10:3306/SPRING");
        ds.setUsername("root");
        ds.setPassword("LYP809834049");
        return ds;
    }

    @Bean(name = "psqlDataSource")
    public DataSource psqlDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://192.168.1.10:5432/cetc10s");
        ds.setUsername("postgres");
        ds.setPassword("LYP809834049");
        return ds;
    }
}
