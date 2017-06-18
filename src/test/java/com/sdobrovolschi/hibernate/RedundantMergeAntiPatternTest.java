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
public class RedundantMergeAntiPatternTest extends DBTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntityManager entityManager;

    // https://vladmihalcea.com/2016/07/19/jpa-persist-and-merge/
    @DataSet("datasets/nplusone/teams-with-members.xml")
    @Test
    public void merge() {
        Team team = teamRepository.get(1L);
        team.changeName("NewTeamName");

        entityManager.merge(team);

        entityManager.flush();
    }
}
