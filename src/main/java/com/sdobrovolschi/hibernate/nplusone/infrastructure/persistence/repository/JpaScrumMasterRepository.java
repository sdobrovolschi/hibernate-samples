package com.sdobrovolschi.hibernate.nplusone.infrastructure.persistence.repository;

import com.sdobrovolschi.hibernate.nplusone.domain.model.ScrumMaster;
import com.sdobrovolschi.hibernate.nplusone.domain.model.ScrumMasterRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("scrumMasterRepository")
public class JpaScrumMasterRepository implements ScrumMasterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ScrumMaster get(Long id) {
        return entityManager.find(ScrumMaster.class, id);
    }
}
