package ru.dorogin.bankemulation.services.utils;

import java.math.BigDecimal;

public class PairBalance {
    public BigDecimal firstBalance;
    public BigDecimal secondBalance;

    public PairBalance(BigDecimal firstBalance, BigDecimal secondBalance) {
        this.firstBalance = firstBalance;
        this.secondBalance = secondBalance;
    }
}
