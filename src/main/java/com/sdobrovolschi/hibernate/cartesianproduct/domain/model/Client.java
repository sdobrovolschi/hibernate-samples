package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private List<Contract> contracts;

    @OneToMany
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private List<Project> projects;

    @SuppressWarnings("unused")
    private Client() { // for JPA
    }

    public Client(String name) {
        hasText(name, "The name must not be empty");
        isTrue(name.length() <= 20, "The name's length must not 20 characters or less");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Contract> getContracts() {
        return unmodifiableList(contracts);
    }

    public List<Project> getProjects() {
        return unmodifiableList(projects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
