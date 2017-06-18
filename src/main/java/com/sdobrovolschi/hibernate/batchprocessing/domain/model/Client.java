package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "CLIENT2")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "client2_seq", allocationSize = 1)
//    @GenericGenerator(
//            name = "sequenceGenerator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "client2_seq"),
//                    @Parameter(name = "optimizer", value = "hilo"),
////                    @Parameter(name = "optimizer", value = "pooled"),
////                    @Parameter(name = "optimizer", value = "pooled-lo"),
//                    @Parameter(name = "initial_value", value = "1"),
//                    @Parameter(name = "increment_size", value = "20")
//            })
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
