package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import javax.persistence.*;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "CLIENT2")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client2_seq")
    @SequenceGenerator(name = "client2_seq", sequenceName = "client2_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @SuppressWarnings("unused")
    private Client() { // for JPA
    }

    public Client(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
