package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.services.AccountService;
import ru.dorogin.bankemulation.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;
    private UserService userService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    @ResponseBody
    List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    // todo исправить возвращаемое значение на String
    @GetMapping("/open")
    @ResponseBody
    Account openAccount(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        return accountService.openAccount(user);
    }
}
