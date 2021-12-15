package ru.dorogin.bankemulation.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "balance")
    private BigDecimal balance;

    public Account(String id, Integer userId, Boolean status, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}
