package com.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int Id;
    String teamName;
    long totalMatches;
    long matchesWon;
    long matchesDrawn;
    float winningPercent;
    LocalDate lastPlayedOn;
    @Transient
    List<MatchEntity> matches;

    public Team(String teamName, long totalMatches, LocalDate lastPlayedOn) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
        this.lastPlayedOn = lastPlayedOn;
    }

    public Team() {
    }
}
