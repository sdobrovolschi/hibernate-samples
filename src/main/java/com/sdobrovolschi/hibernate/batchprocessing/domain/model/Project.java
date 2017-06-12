package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository.YearMonthConverter;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "PROJECT2")
public class Project {

    @EmbeddedId
    private ProjectCode code;

    @Column(name = "NAME")
    private String name;

    @Version
    private int version;

    @ElementCollection
    @CollectionTable(name = "PROJECT_REVENUE", joinColumns = @JoinColumn(name = "PROJECT_CODE", nullable = false))
    @MapKeyColumn(name = "YEAR_MONTH")
    @Convert(converter = YearMonthConverter.class, attributeName = "key")
    private Map<YearMonth, Money> revenues;

    @SuppressWarnings("unused")
    private Project() { //for JPA
    }

    public Project(ProjectCode code, String name) {
        notNull(code, "The code must not be null");
        hasText(name, "The name must not be empty");
        this.code = code;
        this.name = name;
        this.revenues = new HashMap<>();
    }

    public ProjectCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void forecastRevenue(YearMonth month, Money amount) {
        revenues.put(month, amount);
    }

    public void clearRevenueForecast(YearMonth month) {
        revenues.remove(month);
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
        return "Project{" +
                "code=" + code +
                '}';
    }
}
