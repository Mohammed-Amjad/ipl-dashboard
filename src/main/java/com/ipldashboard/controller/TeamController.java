package com.ipldashboard.controller;

import static io.micrometer.core.instrument.util.StringUtils.isEmpty;
import static java.util.Objects.isNull;

import com.ipldashboard.model.MatchEntity;
import com.ipldashboard.response.MatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ipldashboard.model.Team;
import com.ipldashboard.repository.MatchRepository;
import com.ipldashboard.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/teams")
    public ResponseEntity getTeam() {

        List<Team> teamList = (List<Team>) this.teamRepository.findAllTeams();

        if (isNull(teamList)) {
            return ResponseEntity.status(500).body("No teams found in db");
        }

        return ResponseEntity.ok().body(teamList);
    }

    @GetMapping("/teams/{teamName}")
    public ResponseEntity getTeam(@PathVariable String teamName) {

        if (isEmpty(teamName)) {
            return ResponseEntity.badRequest().body("Invalid Team name.");
        }

        Team team = this.teamRepository.findByTeamName(teamName);

        if (isNull(team)) {
            return ResponseEntity.badRequest().body("Team does not have any match.");
        }

        team.setMatches(this.matchRepository.getLatestFewMatchesOfTeam(teamName, 4));

        return ResponseEntity.ok().body(team);
    }

    @GetMapping("/teams/{teamName}/matches")
    public ResponseEntity getMatches(@PathVariable String teamName,
                                     @RequestParam int year) {

        if (isEmpty(teamName)) {
            return ResponseEntity.badRequest().body("Invalid Team name.");
        }

        MatchEntity firstMatch = this.matchRepository.findFirstByTeam1OrTeam2OrderByDate(teamName, teamName);
        MatchEntity lastMatch = this.matchRepository.findFirstByTeam1OrTeam2OrderByDateDesc(teamName, teamName);

        if (isNull(firstMatch) || isNull(lastMatch)) {
            return ResponseEntity.badRequest().body("No team found with teamName " + teamName + ".");
        }

        int firstYear = firstMatch.getDate().getYear();
        int lastYear = lastMatch.getDate().getYear();

        if (isNull(year) || year > lastYear || year < firstYear) {
            return ResponseEntity.badRequest().body("Invalid query param year. Year should be in between "
                    + firstYear + "and " + lastYear + "." );
        }

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);

        List<MatchEntity> matches = this.matchRepository
                .getMatchesOfTeamBetweenDate(teamName, startDate, endDate);

        MatchResponse matchResponse = new MatchResponse(matches, firstYear, lastYear);

        return ResponseEntity.ok().body(matchResponse);
    }
}
