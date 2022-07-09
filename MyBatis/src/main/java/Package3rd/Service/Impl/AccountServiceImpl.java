package Package3rd.Service.Impl;

import Package3rd.Dao.AccountDao;
import Package3rd.Domain.Account;
import Package3rd.Service.AccountService;
import Package3rd.Service.LogRecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Autowired
    private LogRecode logRecode;

    @Override
    public void transfer(String name1, String name2, double money) {
        //  一般情况下下面是一个事务，依旧是同成功同失败
        //  需要开一个全新的事务
        try{
            accountDao.inMoney(name2,money);
            //  模拟异常情况
//            int err = 10/0;
            accountDao.outMoney(name1,money);
        }finally {
            logRecode.getLog(name1,name2,money);
        }
    }

    @Override
    public List<Account> selectInfo(String name) {
        return accountDao.selectMoney(name);
    }

}
