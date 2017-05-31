package com.sdobrovolschi.hibernate.nplusone.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "TEAM_MEMBER")
public class Member implements Serializable {

    @Id
    @Column(name = "TEAM_ID")
    private Long teamId;

    @Id
    @Column(name = "USERNAME")
    private String username;

    @SuppressWarnings("unused")
    private Member() { //for JPA
    }

    public Member(Long teamId, String username) {
        notNull(teamId, "The team id must not be null");
        hasText(username, "The username must not be empty");
        isTrue(username.length() <= 20, "The username's length must not 20 characters or less");
        this.teamId = teamId;
        this.username = username;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(username, member.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return username;
    }
}
