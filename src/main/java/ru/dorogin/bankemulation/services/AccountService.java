package ru.dorogin.bankemulation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.*;

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

    Account checkAccountOwnership(String accountId, User user) throws SecurityException{
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account == null || account.getUserId() != user.getId())
            throw new SecurityException("Отказано в доступе");
        else
            return account;
    }

    public String closeAccount(User user, String accountId) throws IllegalStateException, SecurityException{
        Account account = checkAccountOwnership(accountId, user);
        if(account.getBalance().compareTo(BigDecimal.ZERO) != 0)
            throw new IllegalStateException("Невозможно закрыть счёт, на котором ненулевой балланс");
        if(!account.getStatus()){
            throw new IllegalStateException("Счёт уже закрыт");
        }
        accountRepository.setStatusById(accountId, false);
        return accountId;
    }

    public String deposit(User user, String accountId, BigDecimal cash) throws IllegalStateException, SecurityException, IllegalArgumentException{
        Account account = checkAccountOwnership(accountId, user);
        if(cash.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Недопустимая сумма для внесения");
        if(!account.getStatus())
            throw new IllegalStateException("Невозможно внести деньги на закрытый счёт");
        account.setBalance(account.getBalance().add(cash));
        accountRepository.setBalance(accountId, account.getBalance());
        return account.getBalance().toString();
    }

}
