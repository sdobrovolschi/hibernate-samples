package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.matchesPattern;

/**
 * @author Stanislav Dobrovolschi
 */
@Embeddable
public class ContractNumber {

    @Column(name = "NUMBER")
    private String value;

    @SuppressWarnings("unused")
    private ContractNumber() { // for JPA
    }

    public ContractNumber(String value) {
        matchesPattern(value, "^\\d{1,8}$");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContractNumber that = (ContractNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
