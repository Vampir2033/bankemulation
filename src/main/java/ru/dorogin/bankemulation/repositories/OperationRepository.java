package ru.dorogin.bankemulation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogin.bankemulation.entities.Operation;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByAccountId(String accountId);
}
