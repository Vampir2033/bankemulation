package ru.dorogin.bankemulation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dorogin.bankemulation.entities.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    public List<Account> findAll();
}
