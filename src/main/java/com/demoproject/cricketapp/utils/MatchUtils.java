package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.*;
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

    public int getTarget(Match match, String bowlingTeamId) {
        int firstInningsScore = Objects.equals(bowlingTeamId, match.getScoreboard().getTeam1().getId()) ? match.getScoreboard().getTeam1Score() : match.getScoreboard().getTeam2Score();
        return firstInningsScore + 1;
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


    public int updateInningsScore(int inningsScore, BallEvent ballEvent)
    {
        String ballResult = ballEvent.getResult();
        if(!Objects.equals(ballResult, "W"))
            inningsScore += Integer.parseInt(ballResult);
        return inningsScore;
    }
    public void updateCurrentPlayers(Map<String, Integer> currentPlayers, BallEvent ballEvent) {
        String ballResult = ballEvent.getResult();
        int batsman1 = currentPlayers.get("batsman1");
        int batsman2 = currentPlayers.get("batsman2");
        if (Objects.equals(ballResult, "1") || Objects.equals(ballResult, "3")) {
            currentPlayers.put("batsman1", batsman2);
            currentPlayers.put("batsman2", batsman1);
        } else if (Objects.equals(ballResult, "W")) {
            currentPlayers.put("batsman1", Math.max(batsman1, batsman2) + 1);
        }
        if (ballEvent.getBallNumber() % 6 == 0) {
            int temp = currentPlayers.get("batsman1");
            currentPlayers.put("batsman1", currentPlayers.get("batsman2"));
            currentPlayers.put("batsman2", temp);
            currentPlayers.put("bowler", (currentPlayers.get("bowler") + 1) % 5); //bowler logic might be changed later.
        }
    }
    public Map<String, Integer> initialiseCurrentPlayers() {
        Map <String, Integer> currentPlayers = new HashMap<>();
        currentPlayers.put("batsman1", 0);
        currentPlayers.put("batsman2", 1);
        currentPlayers.put("bowler", 0);
        return currentPlayers;
    }

    public List<Player> getTopFiveBowlers(List<Player> bowlingTeamPlayers) {
        bowlingTeamPlayers.sort(Comparator.comparingInt(Player::getBowlingSkill).reversed());
        return bowlingTeamPlayers.subList(0, 5);
    }

    public void getBattingOrderSorted(List<Player> battingTeamPlayers) {
        battingTeamPlayers.sort(Comparator.comparingInt(Player::getBattingSkill).reversed());
    }

    public static List<Player> getTeamPlayersFromScoreboard(Scoreboard scoreboard, String teamId) {
        if (Objects.equals(teamId, scoreboard.getTeam1().getId()))
            return scoreboard.getTeam1().getPlayers();;
        return scoreboard.getTeam2().getPlayers();
    }
}
