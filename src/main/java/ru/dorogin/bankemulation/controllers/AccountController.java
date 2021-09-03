package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.services.AccountService;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @GetMapping
    List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
