package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Embeddable
public class Money {

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @SuppressWarnings("unused")
    private Money() { //for JPA
    }

    private Money(BigDecimal amount, Currency currency) {
        notNull(amount, "The amount must not be null");
        notNull(currency, "The currency must not be null");
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
