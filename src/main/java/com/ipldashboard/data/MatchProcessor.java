package com.ipldashboard.data;

import org.springframework.batch.item.ItemProcessor;
import com.ipldashboard.model.MatchEntity;
import java.time.LocalDate;
import java.util.Locale;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

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

        String tossWinner = matchData.getTossWinner().trim();
        String team1 = matchData.getTeam1().trim();
        String team2 = matchData.getTeam2().trim();

        if (isBlank(team1) || isBlank(team2) || isBlank(tossWinner)) {
            return null;
        }

        if ("bat".equalsIgnoreCase(tossWinner)) {
            matchEntity.setTeam1(tossWinner);
            matchEntity.setTeam2(tossWinner.equalsIgnoreCase(team2)
                    ? team2 : team1);
        } else {
            matchEntity.setTeam2(tossWinner);
            matchEntity.setTeam1(tossWinner.equalsIgnoreCase(team1)
                    ? team2 : team1);
        }

        matchEntity.setTeam1(matchData.getTeam1());
        matchEntity.setTeam2(matchData.getTeam2());
        matchEntity.setUmpire1(matchData.getUmpire1());
        matchEntity.setUmpire2(matchData.getUmpire2());
        matchEntity.setVenue(matchData.getVenue());

        String matchWinner = matchData.getWinner();
        if (isBlank(matchWinner)
                || "NA".equalsIgnoreCase(matchWinner)
                || "N/A".equalsIgnoreCase(matchWinner)) {
            matchWinner = "tie";
        }

        matchEntity.setWinner(matchWinner);

        return matchEntity;
    }
}
