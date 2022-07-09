package Package3rd.Dao;

import Package3rd.Domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AccountDao {
    @Update("update account set money = money + #{money} where name = #{name}")
    void inMoney(@Param("name") String name,@Param("money") double money);

    @Update("update account set money = money - #{money} where name = #{name}")
    void outMoney(@Param("name") String name,@Param("money") double money);

    @Select("select * from account where name = #{name}")
    List<Account> selectMoney(@Param("name") String name);

}
