package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import java.math.BigDecimal;

/**
 * @author Stanislav Dobrovolschi
 */
public enum Currency {

    GBP;

    public Money amountOf(BigDecimal amount) {
        return Money.of(amount, this);
    }
}
