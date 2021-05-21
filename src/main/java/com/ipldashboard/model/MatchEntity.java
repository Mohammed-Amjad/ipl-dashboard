package com.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MatchEntity {

    @Id
    int id;
    String city;
    LocalDate date;
    String playerOfMatch;
    String venue;
    String neutralVenue;
    String team1;
    String team2;
    String tossWinner;
    String tossDecision;
    String winner;
    String result;
    String resultMargin;
    String eliminator;
    String method;
    String umpire1;
    String umpire2;
}
