package ru.dorogin.bankemulation.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Account {
    String id;
    Boolean status;
    BigDecimal balance;
}
