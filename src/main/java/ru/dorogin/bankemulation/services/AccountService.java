package ru.dorogin.bankemulation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.AccountRepository;
import ru.dorogin.bankemulation.services.utils.PairBalance;

import java.math.BigDecimal;
import java.util.*;

// todo вывести список счетов

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private OperationService operationService;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public String openAccount(User user){
        String accountNumber;
        Random random = new Random();
        do {
            accountNumber = "";
            for (int i = 0; i < 20; i++)
                accountNumber += random.nextInt(10);
        }while(accountRepository.findById(accountNumber).isPresent());
        Account account = new Account(accountNumber, user.getId(), true, BigDecimal.valueOf(0));
        accountRepository.save(account);
        return account.getId();
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
        operationService.addOperationClose(account);
        return accountId;
    }

    void checkPossibilityToDeposit(Account account, BigDecimal cash)
            throws IllegalArgumentException, IllegalStateException{
        if(cash.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Некорректная сумма операции");
        if(!account.getStatus())
            throw new IllegalStateException("Невозможно внести деньги на закрытый счёт");
    }

    public String deposit(User user, String accountId, BigDecimal cash)
            throws IllegalStateException, SecurityException, IllegalArgumentException{
        Account account = checkAccountOwnership(accountId, user);
        checkPossibilityToDeposit(account, cash);
        account.setBalance(account.getBalance().add(cash));
        accountRepository.setBalance(accountId, account.getBalance());
        operationService.addOperationDeposit(account, cash);
        return account.getBalance().toString();
    }

    void checkPossibilityToRemoving(Account account, BigDecimal cash)
            throws IllegalArgumentException, IllegalStateException{
        if(cash.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Некорректная сумма для снятия");
        if(account.getBalance().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalStateException("Снятие денег с пустого счёта недопустимо");
        if(cash.compareTo(account.getBalance()) > 0)
            throw new IllegalArgumentException("Невозможно снять денег больше, чем есть на счёте");
    }


    public String removing(User user, String accountId, BigDecimal cash) throws IllegalStateException, SecurityException, IllegalArgumentException{
        Account account = checkAccountOwnership(accountId, user);
        checkPossibilityToRemoving(account, cash);
        account.setBalance(account.getBalance().subtract(cash));
        accountRepository.setBalance(accountId, account.getBalance());
        operationService.addOperationRemoving(account, cash);
        return account.getBalance().toString();
    }

    public Object transferring(User user, String fromAccountId, String toAccountId, BigDecimal cash)
            throws IllegalStateException, SecurityException, IllegalArgumentException{
        Account fromAccount = checkAccountOwnership(fromAccountId, user);
        checkPossibilityToRemoving(fromAccount, cash);
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new SecurityException("Счёт получателя не найден"));
        if(fromAccount.getId() == toAccount.getId()){
            throw new IllegalArgumentException("Счета отправителя и получателя имеют одинаковое значение");
        }
        checkPossibilityToDeposit(toAccount, cash);
        fromAccount.setBalance(fromAccount.getBalance().subtract(cash));
        accountRepository.setBalance(fromAccountId, fromAccount.getBalance());
        toAccount.setBalance(toAccount.getBalance().add(cash));
        accountRepository.setBalance(toAccountId, toAccount.getBalance());
        operationService.addOperationTransferring(fromAccount, cash);
        return new PairBalance(fromAccount.getBalance(), toAccount.getBalance());
    }

}
