package com.sdobrovolschi.hibernate.extralazy;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.nplusone.domain.model.Team;
import com.sdobrovolschi.hibernate.nplusone.domain.model.TeamRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Stanislav Dobrovolschi
 */
public class ExtraLazyTest extends DBTest {

    @Autowired
    private TeamRepository teamRepository;

    // more efficient query by @LazyCollection(LazyCollectionOption.EXTRA)
    @DataSet("datasets/nplusone/teams-with-members.xml")
    @Test
    public void extraLazyCollection() {
        Team team = teamRepository.get(1L);

        System.out.println(team.getMembers().size());
//        System.out.println(team.getMembers().iterator().next());

        assertThatDataSource().hasSelectCount(2);
    }
}
