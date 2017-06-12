package com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.batchprocessing.domain.model.Project;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.ProjectCode;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.ProjectRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository
public class JpaProjectRepository implements ProjectRepository {

    private final EntityManager entityManager;

    public JpaProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Project project) {
//        entityManager.unwrap(Session.class).setJdbcBatchSize(20);
        entityManager.persist(project);
    }

    @Override
    public Project get(ProjectCode projectCode) {
//       entityManager.unwrap(Session.class).setJdbcBatchSize(20);
       return entityManager.find(Project.class, projectCode);
    }

    @Override
    public void delete(ProjectCode projectCode) {
        entityManager.remove(get(projectCode));
    }
}
