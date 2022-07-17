package Package3rd.dao;

import Package3rd.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountDao {
    //  查询全部
    @Select("SELECT * FROM account")
    public List<Account> findAll();

    //  通过ID查询
    @Select("SELECT * FROM account WHERE id = #{id}")
    public List<Account> findById(int id);

    //  通过Name查询
    @Select("SELECT * FROM account WHERE name = #{name}")
    public List<Account> findByName(String name);

    //  通过Money查询
    @Select("SELECT * FROM account WHERE money = #{money}")
    public List<Account> findByMoney(double money);
}
