package Package3rd.Service;

import Package3rd.Domain.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AccountService {
    //  转账
    @Transactional
    void transfer(String name1,String name2,double money);
    //  查询
    @Transactional
    List<Account> selectInfo(String name);
}
