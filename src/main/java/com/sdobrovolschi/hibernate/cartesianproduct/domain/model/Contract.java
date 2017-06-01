package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import javax.persistence.*;

import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "CONTRACT")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
    @SequenceGenerator(name = "contract_seq", sequenceName = "contract_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private ContractNumber number;

    @SuppressWarnings("unused")
    private Contract() { // for JPA
    }

    public Contract(ContractNumber number) {
        notNull(number, "The contract number must not be null");
        this.number = number;
    }

    public ContractNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contract contract = (Contract) o;
        return Objects.equals(number, contract.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
