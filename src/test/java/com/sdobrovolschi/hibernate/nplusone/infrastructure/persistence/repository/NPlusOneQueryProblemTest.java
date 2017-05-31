package com.sdobrovolschi.hibernate.nplusone.infrastructure.persistence.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.nplusone.domain.model.ScrumMaster;
import com.sdobrovolschi.hibernate.nplusone.domain.model.ScrumMasterRepository;
import com.sdobrovolschi.hibernate.nplusone.domain.model.Team;
import com.sdobrovolschi.hibernate.nplusone.domain.model.TeamRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stanislav Dobrovolschi
 */
@DataSet("datasets/nplusone/teams-with-members.xml")
public class NPlusOneQueryProblemTest extends DBTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ScrumMasterRepository scrumMasterRepository;

    // can be fixed by
    // 1. @BatchSize on collection
    // 2. hibernate.default_batch_fetch_size
    // 3. @Fetch(FetchMode.SUBSELECT)
    @Test
    public void NPlusOneOnCollection() {
        List<Team> teams = teamRepository.getAll();

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);

        assertThat(teams).hasSize(3);

        assertThatDataSource().hasSelectCount(2);
    }

    // can be fixed by
    // 1. @BatchSize on entity
    // 2. hibernate.default_batch_fetch_size
    @Test
    public void NPlusOneOnEntity() {
        List<Team> teams = teamRepository.getAll();

        teams.stream()
                .peek(System.out::println)
                .map(Team::getScrumMasterUsername)
                .forEach(System.out::println);

        assertThat(teams).hasSize(3);

        assertThatDataSource().hasSelectCount(2);
    }

    // can be fixed by calling get by id first
    @Test
    public void batchSizeBreaksGetByPK() {
        List<Team> teams = teamRepository.getAll();

        ScrumMaster scrumMaster = scrumMasterRepository.get(1L);

        assertThat(teams).hasSize(3);
        assertThat(scrumMaster).isNotNull();

        assertThatDataSource().hasSelectCount(2);
    }

    // can be fixed by
    // 1. disrinct in hql + QueryHints.PASS_DISTINCT_THROUGH = false https://hibernate.atlassian.net/browse/HHH-10965
    // 2. wrap into a LinkedHashSet
    @Test
    public void joinFetch() {
        List<Team> teams = teamRepository.getAllWithMembers();

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);


        assertThat(teams).hasSize(3);

        assertThatDataSource().hasSelectCount(1);
    }

    @Test
    public void entityGraph() {
        List<Team> teams = teamRepository.getAllFetchMembers();

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);

        assertThat(teams).hasSize(3);

        assertThatDataSource().hasSelectCount(1);
    }

    @Test
    public void joinFetchInMemoryPagination() {
        List<Team> teams = teamRepository.getAllWithMembers(0, 2);

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);

        assertThat(teams).hasSize(2);

        assertThatDataSource().hasSelectCount(1);
    }

    @Test
    public void entityGraphInMemoryPagination() {
        List<Team> teams = teamRepository.getAllFetchMembers(0, 2);

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);

        assertThat(teams).hasSize(2);

        assertThatDataSource().hasSelectCount(1);
    }

    // https://hibernate.atlassian.net/browse/HHH-304
    @Test
    public void subSelectDoesNotApplyLimitToCollection() {
        List<Team> teams = teamRepository.getAll(0, 2);

        teams.stream()
                .peek(System.out::println)
                .map(Team::getMembers)
                .flatMap(Set::stream)
                .forEach(System.out::println);

        assertThat(teams).hasSize(2);

        assertThatDataSource().hasSelectCount(2);
    }
}