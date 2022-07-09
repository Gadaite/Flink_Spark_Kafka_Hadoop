package Package2nd.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class JdbcConfig {

    @Value(value = "com.mysql.cj.jdbc.Driver")
    private String driver;

    @Value(value = "jdbc:mysql://192.168.1.10:3306/SPRING?useSSL=false")
    private String url;

    @Value(value = "root")
    private String username;

    @Value(value = "LYP809834049")
    private String password;

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
    //  开启事务管理，使用的是jdbc事务
    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        DataSourceTransactionManager tam = new DataSourceTransactionManager();
        tam.setDataSource(dataSource);
        return tam;
    }
}
