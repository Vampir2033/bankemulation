package ru.dorogin.bankemulation.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

enum OperationType{
    DEPOSIT,
    WRITE_OFF,
    TRANSLATION,
    CLOSE
}

@Entity
public class Operation {
    private int id;
    private String accountId;
    private OperationType operation;
    private BigDecimal money;
    private Timestamp timeOperation;
}
