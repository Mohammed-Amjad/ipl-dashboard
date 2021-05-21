package com.ipldashboard.data;

import org.springframework.batch.item.ItemProcessor;
import com.ipldashboard.model.MatchEntity;
import java.time.LocalDate;

public class MatchProcessor implements ItemProcessor<MatchData, MatchEntity> {

    @Override
    public MatchEntity process(MatchData matchData) throws Exception {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(Integer.parseInt(matchData.getId()));
        matchEntity.setCity(matchData.getCity());
        matchEntity.setDate(LocalDate.parse(matchData.getDate()));
        matchEntity.setEliminator(matchData.getEliminator());
        matchEntity.setMethod(matchData.getMethod());
        matchEntity.setNeutralVenue(matchData.getNeutralVenue());
        matchEntity.setPlayerOfMatch(matchData.getPlayerOfMatch());
        matchEntity.setResult(matchData.getResult());
        matchEntity.setResultMargin(matchData.getResultMargin());
        matchEntity.setTossDecision(matchData.getTossDecision());
        matchEntity.setTossWinner(matchData.getTossWinner());
        if (matchData.getTossDecision() == "bat") {
            matchEntity.setTeam1(matchData.getTossWinner());
            matchEntity.setTeam2(matchData.getTossWinner() == matchData.getTeam1()
                    ? matchData.getTeam2() : matchData.getTeam1());
        } else {
            matchEntity.setTeam2(matchData.getTossWinner());
            matchEntity.setTeam1(matchData.getTossWinner() == matchData.getTeam1()
                    ? matchData.getTeam2() : matchData.getTeam1());
        }
        matchEntity.setUmpire1(matchData.getUmpire1());
        matchEntity.setUmpire2(matchData.getUmpire2());
        matchEntity.setVenue(matchData.getVenue());
        matchEntity.setWinner(matchData.getWinner());
        return matchEntity;
    }
}
