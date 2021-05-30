package com.ipldashboard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ipldashboard.model.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("SELECT m FROM Team m ORDER BY m.winningPercent DESC")
    List<Team> findAllTeams();

    Team findByTeamName(String teamName);
}
