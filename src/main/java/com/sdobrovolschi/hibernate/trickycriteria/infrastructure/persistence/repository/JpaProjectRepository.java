package com.sdobrovolschi.hibernate.trickycriteria.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.trickycriteria.domain.model.Project;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.ProjectRepository;
import com.sdobrovolschi.hibernate.trickycriteria.domain.model.ProjectSearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("asdas")
public class JpaProjectRepository implements ProjectRepository {

    private final EntityManager entityManager;

    public JpaProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Project> search(ProjectSearchCriteria searchCriteria, int offset, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> query = builder.createQuery(Project.class);
        Root<Project> project = query.from(Project.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.code != null) {
            predicates.add(builder.like(project.get("code").get("value"), "%" + searchCriteria.code + "%"));
        }

//        ParameterExpression<BigDecimal> budget = builder.parameter(BigDecimal.class);
        if (searchCriteria.budget != null) {
            predicates.add(builder.equal(project.get("budget"), searchCriteria.budget));
        }

        if (searchCriteria.start != null) {
            predicates.add(builder.greaterThanOrEqualTo(project.get("start"), searchCriteria.start));
        }

        if (searchCriteria.end != null) {
            predicates.add(builder.lessThanOrEqualTo(project.get("end"), searchCriteria.end));
        }

        query.select(project)
                .where(predicates.toArray(new Predicate[]{}));

        //if (searchCriteria.budget != null) {
//            typedQuery.setParameter(budget, searchCriteria.budget);
//        }
        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

//    @Override
//    public List<Project> search(ProjectSearchCriteria searchCriteria, int offset, int pageSize) {
//        JPAQuery<Project> from = new JPAQueryFactory(entityManager).selectFrom(QProject.project);
//
//        if (searchCriteria.code != null) {
//            from.where(QProject.project.code.value.like("%" + searchCriteria.code + "%"));
//        }
//
//        if (searchCriteria.budget != null) {
//            from.where(QProject.project.budget.eq(searchCriteria.budget));
//        }
//
//        if (searchCriteria.start != null) {
//            from.where(QProject.project.start.goe(searchCriteria.start));
//        }
//
//        if (searchCriteria.end != null) {
//            from.where(QProject.project.end.loe(searchCriteria.end));
//        }
//
//        return from.offset(offset).limit(pageSize).fetch();
//    }
}
