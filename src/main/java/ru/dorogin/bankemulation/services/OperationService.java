package ru.dorogin.bankemulation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dorogin.bankemulation.entities.Account;
import ru.dorogin.bankemulation.entities.Operation;
import ru.dorogin.bankemulation.entities.OperationType;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.OperationRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OperationService {
    private OperationRepository operationRepository;
    private AccountService accountService;

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<Operation> getAccountOperations(User user, String accountId){
        Account account = accountService.checkAccountOwnership(accountId, user);
        return operationRepository.findByAccountId(account.getId());
    }

    public void addOperationDeposit(Account account, BigDecimal cash){
        Operation operation =
                new Operation(account.getId(), OperationType.DEPOSIT, cash, new Timestamp(System.currentTimeMillis()));
        operationRepository.save(operation);
    }

    public void addOperationRemoving(Account account, BigDecimal cash){
        Operation operation =
                new Operation(account.getId(), OperationType.REMOVIG, cash, new Timestamp(System.currentTimeMillis()));
        operationRepository.save(operation);
    }

    public void addOperationTransferring(Account account, BigDecimal cash){
        Operation operation =
                new Operation(account.getId(), OperationType.TRANSFERRING, cash, new Timestamp(System.currentTimeMillis()));
        operationRepository.save(operation);
    }

    public void addOperationClose(Account account){
        Operation operation =
                new Operation(account.getId(), OperationType.CLOSE, new Timestamp(System.currentTimeMillis()));
        operationRepository.save(operation);
    }
}
