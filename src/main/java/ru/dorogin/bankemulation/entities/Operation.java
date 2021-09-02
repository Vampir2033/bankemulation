package ru.dorogin.bankemulation.entities;

import javax.persistence.Entity;
import java.sql.Timestamp;

enum OperationType{
    DEPOSIT,
    WRITE_OFF,
    TRANSLATION,
    CLOSE
}

@Entity
public class Operation {
    String accountId;
    OperationType operation;
    Timestamp time;
}
