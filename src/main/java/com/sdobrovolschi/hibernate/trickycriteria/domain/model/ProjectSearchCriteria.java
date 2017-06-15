package com.sdobrovolschi.hibernate.trickycriteria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Stanislav Dobrovolschi
 */
public class ProjectSearchCriteria {

    public final String code;
    public final BigDecimal budget;
    public final LocalDate start;
    public final LocalDate end;

    public ProjectSearchCriteria(String code, BigDecimal budget, LocalDate start, LocalDate end) {
        this.code = code;
        this.budget = budget;
        this.start = start;
        this.end = end;
    }
}
