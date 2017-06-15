package com.sdobrovolschi.hibernate.trickycriteria.infrastructure.persistence.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.Project;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.ProjectCode;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.ProjectRepository;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.ProjectSearchCriteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stanislav Dobrovolschi
 */
public class TrickyCriteriaProblemTest extends DBTest {

    @Autowired
    private ProjectRepository projectRepository;

    @DataSet("datasets/trickycriteria/projects.xml")
    @Test
    public void inlinednumericparameter() {
        ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria("AG526348", BigDecimal.valueOf(5000),
                LocalDate.of(2015, 5, 1), LocalDate.of(2018, 11, 1));

        List<Project> projects = projectRepository.search(searchCriteria, 0, 5);

        assertThat(projects)
                .extracting(Project::getCode)
                .containsExactly(ProjectCode.of("AG526348"));

        assertThatDataSource().hasSelectCount(1);
    }
}