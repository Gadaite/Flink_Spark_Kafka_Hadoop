package Package3rd.controller;

import Package3rd.entity.Account;
import Package3rd.service.impl.SelectAccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping(value = "/account")
//@RestController
public class AccountController {
    @Autowired
    private SelectAccountImpl selectAccount;

    @GetMapping("/allCount")
    public List<Account> getAllAccount(){
        return selectAccount.findAll();
    }

    @GetMapping("/id/{id}")
    public List<Account> getById(@PathVariable int id){
        return selectAccount.findByiId(id);
    }

    @GetMapping("/id/{name}")
    public List<Account> getByName(@PathVariable String name){
        return selectAccount.findByName(name);
    }
    @GetMapping("/id/{money}")
    public List<Account> getByMoney(@PathVariable double money){
        return selectAccount.findByMoney(money);
    }
}
