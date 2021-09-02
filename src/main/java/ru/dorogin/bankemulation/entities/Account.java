package ru.dorogin.bankemulation.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

//@Entity
public class Account {
    private String id;
    private Integer userId;
    private Boolean status;
    private BigDecimal balance;
}
