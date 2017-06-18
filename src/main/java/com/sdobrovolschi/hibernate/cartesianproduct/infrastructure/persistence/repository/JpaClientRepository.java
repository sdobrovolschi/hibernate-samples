package com.sdobrovolschi.hibernate.cartesianproduct.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.Client;
import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.ClientRepository;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("clientRepository")
public class JpaClientRepository implements ClientRepository {

    private final EntityManager entityManager;

    public JpaClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Client> getAllWithContractsAndProjects() {
        String query = "select distinct c " +
                "from Client c " +
                "   left join fetch c.contracts " +
                "   left join fetch c.projects";

        return entityManager.createQuery(query, Client.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }

    @Override
    public List<Client> getAll() {
        return entityManager.createQuery("from Client", Client.class)
//                .setHint(QueryHints.READ_ONLY, true)
                .getResultList();
    }
}
