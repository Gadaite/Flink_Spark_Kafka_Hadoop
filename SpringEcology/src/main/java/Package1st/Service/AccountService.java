package Package1st.Service;

import Package1st.Entity.Account;
import Package1st.Mapper.AccountMapper;
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
