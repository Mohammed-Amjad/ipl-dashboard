package com.ipldashboard.data;

import static java.util.Objects.nonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ipldashboard.model.Team;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@Transactional
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamMap = new TreeMap<>();

            em.createQuery("SELECT m.team1, count(*) FROM MatchEntity m GROUP BY m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e ->
                            {
                                LocalDate lastPlayedOn = (LocalDate)
                                        em.createQuery("SELECT max(m.date) FROM MatchEntity m WHERE m.team1=:teamName")
                                        .setParameter("teamName", e[0]).getSingleResult();

                                return new Team((String) e[0], (long) e[1],lastPlayedOn);
                            }
                    )
                    .forEach(team -> teamMap.put(team.getTeamName(), team));

            em.createQuery("SELECT m.team2, count(*) FROM MatchEntity m GROUP BY m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        LocalDate lastPlayedOn = (LocalDate)
                                em.createQuery("SELECT max(m.date) FROM MatchEntity m WHERE m.team2=:teamName")
                                        .setParameter("teamName", e[0]).getSingleResult();

                        Team team = teamMap.get((String) e[0]);

                        if (team.getLastPlayedOn().isBefore(lastPlayedOn)) {
                            team.setLastPlayedOn(lastPlayedOn);
                        }
                        team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                    });

            em.createQuery("SELECT m.winner, count(*) FROM MatchEntity m GROUP BY m.winner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamMap.get((String) e[0]);
                        if (nonNull(team))
                            team.setMatchesWon((long) e[1]);
                    });

            teamMap.values().forEach(team -> {
                long matchesDrawn = em.createQuery("SELECT count(*) FROM MatchEntity m WHERE (m.team1 =:teamName or m.team2 =:teamName) and (m.result=:tie or m.result=:na)", Long.class)
                        .setParameter("teamName", team.getTeamName())
                        .setParameter("tie", "tie")
                        .setParameter("na", "NA")
                        .getSingleResult();
                team.setMatchesDrawn(nonNull(matchesDrawn) ? matchesDrawn : 0l);
                team.setWinningPercent(team.getMatchesWon() * 100/ (team.getTotalMatches() - team.getMatchesDrawn()));
                em.persist(team);
            });

            em.createQuery("SELECT m FROM Team m ORDER BY m.lastPlayedOn DESC")
                    .getResultList()
                    .stream()
                    .forEach(e -> System.out.println(((Team) e).getTeamName()));
        }
    }
}
