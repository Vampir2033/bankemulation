package ru.dorogin.bankemulation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dorogin.bankemulation.entities.Account;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public List<Account> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.status = ?2 WHERE a.id = ?1")
    void setStatusById(String accountId, boolean status);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.balance = ?2 WHERE a.id = ?1")
    void setBalance(String accountId, BigDecimal status);

    public List<Account> findByUserId(Integer userId);
}
