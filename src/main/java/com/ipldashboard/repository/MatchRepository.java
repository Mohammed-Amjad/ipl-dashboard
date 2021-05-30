package com.ipldashboard.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ipldashboard.model.MatchEntity;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {

    List<MatchEntity> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    MatchEntity findFirstByTeam1OrTeam2OrderByDateDesc(String team1, String team2);

    MatchEntity findFirstByTeam1OrTeam2OrderByDate(String team1, String team2);

    @Query("select m from MatchEntity m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")
    List<MatchEntity> getMatchesOfTeamBetweenDate(
            @Param("teamName") String teamName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    default List<MatchEntity> getLatestFewMatchesOfTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
