package ru.dorogin.bankemulation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account openAccount(User user){
        String accountNumber;
        Random random = new Random();
        do {
            accountNumber = "";
            for (int i = 0; i < 20; i++)
                accountNumber += random.nextInt(10);
        }while(accountRepository.findById(accountNumber).isPresent());
        Account account = new Account(accountNumber, user.getId(), true, BigDecimal.valueOf(0));
        accountRepository.save(account);
        return account;
    }
}
