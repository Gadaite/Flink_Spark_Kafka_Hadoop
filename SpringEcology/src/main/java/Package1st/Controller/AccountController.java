package Package1st.Controller;

import Package1st.Entity.Account;
import Package1st.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/all")
    public List<Account> getAll() {
        return accountService.findAll();
    }
}
