package com.sdobrovolschi.hibernate.nplusone.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.sdobrovolschi.hibernate.nplusone.domain.model.Team.MEMBERS_GRAPH;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "TEAM")
@NamedEntityGraph(name = MEMBERS_GRAPH, attributeNodes = @NamedAttributeNode("members"))
public class Team {

    public static final String MEMBERS_GRAPH = "Team[members]";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", sequenceName = "team_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCRUM_MASTER_ID", nullable = false)
    private ScrumMaster scrumMaster;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TEAM_ID", updatable = false)
//    @BatchSize(size = 50)
//    @Fetch(FetchMode.SUBSELECT)
//    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Member> members;

    @SuppressWarnings("unused")
    private Team() { // for JPA
    }

    public Team(String name, ScrumMaster scrumMaster) {
        hasText(name, "The name must not be empty");
        isTrue(name.length() <= 20, "The name's length must not 20 characters or less");
        notNull(scrumMaster, "The scrum muster must not be null");
        this.name = name;
        this.scrumMaster = scrumMaster;
        this.members = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getScrumMasterUsername() {
        return scrumMaster.getUsername();
    }

    public Set<Member> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void addMember() {
        members.add(new Member(id, "asdadas"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
