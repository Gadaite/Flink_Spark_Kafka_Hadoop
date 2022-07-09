package Package2nd.Service;

import Package2nd.Domain.Account;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    /**
     * 有些异常默认不进行回滚
     * 手动设置参数即可
     */
    @Transactional(rollbackFor = {IOException.class,NullPointerException.class})
    public void transfer(String name1,String name2,double money);

    public List<Account> selectMoney(String name);
}
