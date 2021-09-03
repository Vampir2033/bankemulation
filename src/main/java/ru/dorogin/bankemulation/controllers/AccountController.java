package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.services.AccountService;
import ru.dorogin.bankemulation.services.UserService;

import java.math.BigDecimal;
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

    @GetMapping("/open")
    @ResponseBody
    String openAccount(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        return accountService.openAccount(user);
    }

    @GetMapping("/close/{accountId}")
    @ResponseBody
    String openAccount(Principal principal, @PathVariable(value = "accountId")String accountId){
        User user = userService.findUserByUsername(principal.getName());
        try {
            return accountService.closeAccount(user, accountId);
        }
        catch (Exception exception){
            return exception.getMessage();
        }
    }

    @GetMapping("/deposit")
    @ResponseBody
    String deposit(Principal principal,
                   @RequestParam(value = "accountId") String accountId,
                   @RequestParam(value = "cash") BigDecimal cash){
        User user = userService.findUserByUsername(principal.getName());
        try {
            return accountService.deposit(user, accountId, cash);
        }
        catch (Exception exception){
            return exception.getMessage();
        }
    }

    @GetMapping("/removing")
    @ResponseBody
    String removing(Principal principal,
                    @RequestParam(value = "accountId") String accountId,
                    @RequestParam(value = "cash") BigDecimal cash){
        User user = userService.findUserByUsername(principal.getName());
        try {
            return accountService.removing(user, accountId, cash);
        }
        catch (Exception exception){
            return exception.getMessage();
        }
    }

    @GetMapping("/transferring")
    @ResponseBody
    Object removing(Principal principal,
                    @RequestParam(value = "fromAccountId") String fromAccountId,
                    @RequestParam(value = "toAccountId") String toAccountId,
                    @RequestParam(value = "cash") BigDecimal cash){
        User user = userService.findUserByUsername(principal.getName());
        try {
            return accountService.transferring(user, fromAccountId, toAccountId, cash);
        }
        catch (Exception exception){
            return exception.getMessage();
        }
    }
}
