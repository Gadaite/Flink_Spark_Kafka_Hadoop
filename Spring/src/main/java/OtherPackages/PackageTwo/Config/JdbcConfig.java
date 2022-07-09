package OtherPackages.PackageTwo.Config;

import OtherPackages.PackageTwo.Dao.Account;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 简单类型的注入以及引用类型的传参使用
 */
public class JdbcConfig {

    @Value(value = "com.mysql.cj.jdbc.Driver")
    private String driver;
    @Value(value = "jdbc:mysql://192.168.1.10:3306/SPRING")
    private String url;
    @Value(value = "root")
    private String username;
    @Value(value = "LYP809834049")
    private String password;

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(Account account){
        System.out.println(account);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
