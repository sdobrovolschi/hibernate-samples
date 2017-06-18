package com.sdobrovolschi.hibernate;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.nplusone.domain.model.Team;
import com.sdobrovolschi.hibernate.nplusone.domain.model.TeamRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author Stanislav Dobrovolschi
 */
public class DirtyCheckingTest extends DBTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntityManager entityManager;

    // TwoPhaseLoad doInitializeEntity - during the hydrate process a snapshot of the loaded entity is taken (EntityEntry)
    // DefaultFlushEntityEventListener's dirtyCheck method - performs dirty checking
    @DataSet("datasets/nplusone/teams-with-members.xml")
    @Test
    public void get() {
        Team team = teamRepository.get(1L);

        team.addMember();

        entityManager.flush();
    }
}
