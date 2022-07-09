package PackageOne.Service;

import PackageOne.Entity.Account;
import PackageOne.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService {
    @Autowired(required = false)
//    @Autowired  //  Could not autowire. No beans of 'AccountMapper' type found.
    private AccountMapper accountMapper;

    public List<Account> findAll(){
        return accountMapper.findAll();
    }
}
