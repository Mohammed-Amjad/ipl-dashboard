package com.ipldashboard.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchData {
    String id;
    String city;
    String date;
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
