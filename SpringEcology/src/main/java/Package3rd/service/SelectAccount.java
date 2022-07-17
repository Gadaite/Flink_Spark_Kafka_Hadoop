package Package3rd.service;

import Package3rd.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectAccount {
    List<Account> findAll();
    List<Account> findByiId(int id);
    List<Account> findByMoney(double money);
    List<Account> findByName(String name);
}
