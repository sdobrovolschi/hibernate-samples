package com.sdobrovolschi.hibernate.nplusone.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.nplusone.domain.model.Team;
import com.sdobrovolschi.hibernate.nplusone.domain.model.TeamRepository;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("teamRepository")
public class JpaTeamRepository implements TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Team> getAll() {
        return entityManager.createQuery("from Team", Team.class)
                .getResultList();
    }

    @Override
    public List<Team> getAllWithMembers() {
        return entityManager.createQuery("select t from Team t left join fetch t.members", Team.class)
//                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }

    @Override
    public List<Team> getAllWithMembers(int offset, int pageSize) {
        return entityManager.createQuery("select t from Team t left join fetch t.members", Team.class)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Team> getAllFetchMembers() {
        return entityManager.createQuery("from Team", Team.class)
                .setHint(QueryHints.FETCHGRAPH, entityManager.getEntityGraph(Team.MEMBERS_GRAPH))
                .getResultList();
    }

    @Override
    public List<Team> getAllFetchMembers(int offset, int pageSize) {
        return entityManager.createQuery("from Team", Team.class)
                .setHint(QueryHints.FETCHGRAPH, entityManager.getEntityGraph(Team.MEMBERS_GRAPH))
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Team> getAll(int offset, int pageSize) {
        return entityManager.createQuery("from Team", Team.class)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public void add(Team team) {
        entityManager.persist(team);
        entityManager.flush();
    }
}
