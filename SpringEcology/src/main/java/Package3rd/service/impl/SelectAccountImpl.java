package Package3rd.service.impl;

import Package3rd.dao.AccountDao;
import Package3rd.entity.Account;
import Package3rd.service.SelectAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selectAccountImpl")
public class SelectAccountImpl implements SelectAccount {
    @Autowired
    private AccountDao accountDao;
    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public List<Account> findByiId(int id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findByMoney(double money) {
        return accountDao.findByMoney(money);
    }

    @Override
    public List<Account> findByName(String name) {
        return accountDao.findByName(name);
    }
}
