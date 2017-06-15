package com.sdobrovolschi.hibernate.trickycriteria.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "PROJECT3")
public class Project {

    @EmbeddedId
    private ProjectCode code;

    @Column(name = "BUDGET")
    private BigDecimal budget;

    @Column(name = "START")
    private LocalDate start;

    @Column(name = "END")
    private LocalDate end;

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

    public BigDecimal getBudget() {
        return budget;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
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

    @Override
    public String toString() {
        return code.toString();
    }
}
