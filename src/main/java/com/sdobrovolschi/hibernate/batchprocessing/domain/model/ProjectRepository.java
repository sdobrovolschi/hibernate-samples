package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

/**
 * @author Stanislav Dobrovolschi
 */
public interface ProjectRepository {

    void add(Project project);

    Project get(ProjectCode projectCode);

    void delete(ProjectCode projectCode);
}
