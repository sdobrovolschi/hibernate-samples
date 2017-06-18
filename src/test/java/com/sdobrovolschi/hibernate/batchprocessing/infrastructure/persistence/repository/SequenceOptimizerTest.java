package com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author Stanislav Dobrovolschi
 */
public class SequenceOptimizerTest extends DBTest {

    @Autowired
    private EntityManager entityManager;

    // uses NoopOptimizer
    // default when allocationSize = 1
    @Test
    public void none() {
        for (int i = 0; i < 20; i++) {
            entityManager.persist(new Client("Client" + i));
        }

        entityManager.flush();

        assertThatDataSource().hasOtherCount(20);
    }

    // uses HiLoOptimizer
    @Test
    public void hiLo() {
        for (int i = 0; i < 20; i++) {
            entityManager.persist(new Client("Client" + i));
        }

        entityManager.flush();

        assertThatDataSource().hasOtherCount(1);
    }

    // uses PooledOptimizer
    // sequence increment size must be equal to generator increment size
    @Test
    public void pooled() {
        for (int i = 0; i < 20; i++) {
            entityManager.persist(new Client("Client" + i));
        }

        entityManager.flush();

        assertThatDataSource().hasOtherCount(2);
    }

    // uses PooledLoOptimizer
    // sequence increment size must be equal to generator increment size
    @Test
    public void pooledLo() {
        for (int i = 0; i < 20; i++) {
            entityManager.persist(new Client("Client" + i));
        }

        entityManager.flush();

        assertThatDataSource().hasOtherCount(1);
    }
}
