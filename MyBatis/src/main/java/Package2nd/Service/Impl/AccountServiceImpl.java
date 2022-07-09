package Package2nd.Service.Impl;

import Package2nd.Dao.AccountDao;
import Package2nd.Domain.Account;
import Package2nd.Service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public List<Account> selectMoney(String name) {
        List<Account> accounts = accountDao.selectMoney(name);
        return accounts;
    }

    @Override
    public void transfer(String name1, String name2, double money) {
        accountDao.outMoney(name1,money);
        //  如果中间出现异常，那么此处业务逻辑上面语句已经执行，但是下面语句并没有执行，明显违背业务逻辑
        //  此时需要在JdbcConfig中开启事务，使用该方法位置使用Transactional注解，执行回滚操作
//        int err = 10/0; //  模拟异常情况
        accountDao.inMoney(name2,money);
    }
}
