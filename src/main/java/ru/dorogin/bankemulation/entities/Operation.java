package ru.dorogin.bankemulation.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "operations")
public class Operation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
//    @ManyToOne()
    private String accountId;

    @Column(name = "operation")
    private OperationType operation;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "time_operation")
    private Timestamp timeOperation;

    public Operation(String accountId, OperationType operation, Timestamp timeOperation) {
        this.accountId = accountId;
        this.operation = operation;
        this.timeOperation = timeOperation;
    }

    public Operation(String accountId, OperationType operation, BigDecimal money, Timestamp timeOperation) {
        this.accountId = accountId;
        this.operation = operation;
        this.money = money;
        this.timeOperation = timeOperation;
    }
}
