package Package3rd.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfig {

    //  创建SqlSessionFactory
    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("Package3rd.entity");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    //  通过Dao创建代理实现
    //  这里直接导致APPlication可以直接从interface中获取bean
    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("Package3rd.dao");
        return msc;
    }
}
