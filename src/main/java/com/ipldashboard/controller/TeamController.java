package com.ipldashboard.controller;

import static io.micrometer.core.instrument.util.StringUtils.isEmpty;
import static java.util.Objects.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ipldashboard.model.Team;
import com.ipldashboard.repository.MatchRepository;
import com.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public ResponseEntity getTeam(@PathVariable String teamName) {

        if (isEmpty(teamName)) {
            return ResponseEntity.badRequest().body("Invalid Team name.");
        }

        Team team = teamRepository.findByTeamName(teamName);

        if (isNull(team)) {
            return ResponseEntity.badRequest().body("Team does not have any match.");
        }

        team.setMatches(matchRepository.getLatestFewMatchesOfTeam(teamName, 4));
        return ResponseEntity.ok().body(team);
    }
}
