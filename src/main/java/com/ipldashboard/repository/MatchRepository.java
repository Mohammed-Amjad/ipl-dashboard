package com.ipldashboard.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.ipldashboard.model.MatchEntity;
import java.util.List;

public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {

    List<MatchEntity> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<MatchEntity> getLatestFewMatchesOfTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
