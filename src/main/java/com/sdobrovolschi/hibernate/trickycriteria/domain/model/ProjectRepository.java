package com.sdobrovolschi.hibernate.trickycriteria.domain.model;

import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
public interface ProjectRepository {

    List<Project> search(ProjectSearchCriteria searchCriteria, int offset, int pageSize);
}
