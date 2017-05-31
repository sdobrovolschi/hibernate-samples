package com.sdobrovolschi.hibernate.nplusone.domain.model;

import javax.persistence.*;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "SCRUM_MASTER")
//@BatchSize(size = 50)
public class ScrumMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scrum_master_seq")
    @SequenceGenerator(name = "scrum_master_seq", sequenceName = "scrum_master_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @SuppressWarnings("unused")
    ScrumMaster() { // for JPA
    }

    public ScrumMaster(String username) {
        hasText(username, "The username must not be empty");
        isTrue(username.length() <= 20, "The username's length must not 20 characters or less");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScrumMaster that = (ScrumMaster) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return username;
    }
}
