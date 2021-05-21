package com.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;
import com.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    Team findByTeamName(String teamName);
}
