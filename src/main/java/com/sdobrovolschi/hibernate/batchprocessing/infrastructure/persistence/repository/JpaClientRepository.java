package com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.batchprocessing.domain.model.Client;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.ClientRepository;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.Dialect;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository
public class JpaClientRepository implements ClientRepository {

    private final EntityManager entityManager;

    public JpaClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void addAll(Collection<? extends Client> clients) {
//        int batchSize = getBatchSize();
//        int i = 0;
        for (Client client : clients) {
            entityManager.persist(client);
//            i++;
//            if (i % batchSize == 0) {
//                entityManager.flush();
//                entityManager.clear();
//            }
        }
    }

    private int getBatchSize() {
        return Integer.valueOf((String) entityManager.getEntityManagerFactory().getProperties()
                .getOrDefault(AvailableSettings.STATEMENT_BATCH_SIZE, Dialect.DEFAULT_BATCH_SIZE));
    }

    @Override
    public Client get(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(get(id));
    }
}
