package com.sdobrovolschi.hibernate.nplusone.domain.model;

import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
public interface TeamRepository {

    List<Team> getAll();

    List<Team> getAllWithMembers();

    List<Team> getAllWithMembers(int offset, int pageSize);

    List<Team> getAllFetchMembers();

    List<Team> getAllFetchMembers(int offset, int pageSize);

    List<Team> getAll(int offset, int pageSize);

    void add(Team team);
}
