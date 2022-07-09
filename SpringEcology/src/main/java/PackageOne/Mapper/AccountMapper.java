package PackageOne.Mapper;

import PackageOne.Entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 需要实现的方法
 */
@Mapper
public interface AccountMapper {
    //  查找所有，需要建立对应xml(SQL)
    List<Account> findAll();
}
