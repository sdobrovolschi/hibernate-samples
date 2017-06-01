package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.matchesPattern;

/**
 * @author Stanislav Dobrovolschi
 */
@Embeddable
public class ProjectCode {

    @Column(name = "CODE")
    private String value;

    @SuppressWarnings("unused")
    private ProjectCode() { // for JPA
    }

    public ProjectCode(String value) {
        matchesPattern(value, "^[A-Z0-9]{1,8}$");
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
        ProjectCode that = (ProjectCode) o;
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
