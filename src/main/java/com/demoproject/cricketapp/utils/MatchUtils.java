package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.Toss;
import com.demoproject.cricketapp.commons.enums.TossChoice;
import com.demoproject.cricketapp.exception.custom.InvalidMatchRequestException;
import lombok.experimental.UtilityClass;

import java.util.*;


@UtilityClass
public class MatchUtils {
    public static void validateTeamForMatch(Team team) {
        if(team.getPlayers().size() != 11) {
            throw new InvalidMatchRequestException("Team doesn't satisfy player count to play match.");
        }
        if(team.getCaptainId() == null || team.getCaptainId().isEmpty()) {
            throw new InvalidMatchRequestException("Team should have a captain assigned to play match.");
        }
    }
    public static void validateOvers(int overs) {
        if (overs <= 0) {
            throw new InvalidMatchRequestException("Invalid match request. The number of overs must be specified and should be greater than zero.");
        }
    }
    public Comparator<BallEvent> comp = new Comparator<BallEvent>() {
        @Override
        public int compare(BallEvent o1, BallEvent o2) {
            if (o1.getIsFirstInnings() != o2.getIsFirstInnings()) {
                if (o1.getIsFirstInnings())
                    return -1; //dont swap
                return 1;
            }
            if (o1.getBallNumber() > o2.getBallNumber())
                return 1; //swap
            else
                return -1;
        }
    };

    public static Toss haveToss(Team team1, Team team2) {
        Random random = new Random();
        Toss toss = new Toss();

        toss.setTossWonByTeamId(random.nextInt(2) == 0 ? team1.getId() : team2.getId());
        toss.setTossChoice(random.nextInt(2) == 0 ? TossChoice.BAT : TossChoice.BOWL);

        return toss;
    }

    public Map<String, String> getBattingAndBowlingTeams(Match match, Boolean isFirstInnings) {
        Map<String, String> teamsMap = new HashMap<>();

        String team1Id = match.getScoreboard().getTeam1().getId();
        String team2Id = match.getScoreboard().getTeam2().getId();
        Toss toss = match.getToss();
        String tossWonByTeamId = toss.getTossWonByTeamId();
        String tossLostByTeamId = Objects.equals(tossWonByTeamId, team1Id) ? team2Id : team1Id;

        String battingTeamId, bowlingTeamId;
        if (toss.getTossChoice() == TossChoice.BAT) {
            battingTeamId = tossWonByTeamId;
            bowlingTeamId = tossLostByTeamId;
        } else {
            battingTeamId = tossLostByTeamId;
            bowlingTeamId = tossWonByTeamId;
        }

        if (!isFirstInnings) {
            // Swap battingTeamId and bowlingTeamId
            String temp = battingTeamId;
            battingTeamId = bowlingTeamId;
            bowlingTeamId = temp;
        }

        teamsMap.put("battingTeamId", battingTeamId);
        teamsMap.put("bowlingTeamId", bowlingTeamId);

        return teamsMap;
    }


    public static String getWinningTeamId(Match match) {
        int team1Score = match.getScoreboard().getTeam1Score();
        int team2Score = match.getScoreboard().getTeam2Score();
        if(team1Score > team2Score) {
            return match.getScoreboard().getTeam1().getId();
        }
        else if(team1Score < team2Score) {
            return match.getScoreboard().getTeam2().getId();
        }
        else {
            return "None";
        }
    }
}
