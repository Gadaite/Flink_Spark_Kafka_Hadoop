package Package1st.Mapper;

import Package1st.Entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 需要实现的方法
 */
@Mapper
public interface AccountMapper {
    //  查找所有，需要建立对应xml(SQL)
    List<Account> findAll();
}
