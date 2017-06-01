package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import javax.persistence.*;

import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private ProjectCode code;

    @SuppressWarnings("unused")
    private Project() { // for JPA
    }

    public Project(ProjectCode code) {
        notNull(code, "The project code must not be null");
        this.code = code;
    }

    public ProjectCode getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(code, project.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
