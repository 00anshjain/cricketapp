package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.*;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.facade.MatchFacade;
import com.demoproject.cricketapp.service.*;
import com.demoproject.cricketapp.utils.MatchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@RequiredArgsConstructor
public class MatchFacadeImpl implements MatchFacade {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MatchService matchService;
    private final ScoreboardService scoreboardService;
    private final BallEventService ballEventService;

    // MatchService methods
    public Match getMatchById(String matchId) {
        return matchService.getMatchById(matchId);
    }

    public Scoreboard getMatchScoreboard(String matchId) {
        return matchService.getMatchScoreboard(matchId);
    }

    public List<MatchInfoResponse> getAllMatches() {
        return matchService.getAllMatches();
    }

    public void validateMatchRequest(MatchRequest matchRequest) {
        Team team1 = teamService.getTeamById(matchRequest.getTeam1Id());
        Team team2 = teamService.getTeamById(matchRequest.getTeam2Id());
        MatchUtils.validateTeamForMatch(team1);
        MatchUtils.validateTeamForMatch(team2);
        MatchUtils.validateOvers(matchRequest.getOvers());
    }
    public Match createMatch(MatchRequest matchRequest) {
        Team team1 = teamService.getTeamById(matchRequest.getTeam1Id());
        Team team2 = teamService.getTeamById(matchRequest.getTeam2Id());
        Scoreboard scoreboard = scoreboardService.createScoreboard(team1, team2);
        Toss toss = MatchUtils.haveToss(team1, team2);
        return Match.builder()
                .id(UUID.randomUUID().toString())
                .toss(toss)
                .overs(matchRequest.getOvers())
                .dateTime(matchRequest.getDateTime())
                .scoreboard(scoreboard).build();
    }
    public Match playInnings(Match match, Boolean isFirstInnings) {
        /*
            teamsMap = {
                "battingTeam": "batting_team_id",
                "bowlingTeam": "bowling_team_id"
            }
        */
        Map<String, String> teamsMap = MatchUtils.getBattingAndBowlingTeams(match, isFirstInnings);
        String battingTeamId = teamsMap.get("battingTeamId");
        String bowlingTeamId = teamsMap.get("bowlingTeamId");
        int target = isFirstInnings ? -1 : MatchUtils.getTarget(match, bowlingTeamId);

        List<Player> battingTeamPlayers, bowlingTeamPlayers;
        Scoreboard scoreboard = match.getScoreboard();

        if (Objects.equals(battingTeamId, match.getScoreboard().getTeam1().getId()))
        {
            battingTeamPlayers = match.getScoreboard().getTeam1().getPlayers();
            bowlingTeamPlayers = match.getScoreboard().getTeam2().getPlayers();
        }
        else
        {
            bowlingTeamPlayers = match.getScoreboard().getTeam1().getPlayers();
            battingTeamPlayers = match.getScoreboard().getTeam2().getPlayers();
        }
        getBattingOrderSorted(battingTeamPlayers);
        List<Player> topFiveBowlers = getTopFiveBowlers(bowlingTeamPlayers);

        long maximumBalls = match.getOvers() * 6L;
//        Map <String, Integer> currentPlayers = new HashMap<>();
//        currentPlayers.put("batsman1", 0);
//        currentPlayers.put("batsman2", 1);
//        currentPlayers.put("bowler", 0);
        int batsman1 = 0, batsman2 = 1, bowler = 0;
        int score = 0;
        for(long ballNumber = 1; ballNumber <= maximumBalls; ballNumber++)
        {
            double wicketProbability = topFiveBowlers.get(bowler).getBowlingSkill() > battingTeamPlayers.get(batsman1).getBattingSkill() ? 0.1 * ((double)topFiveBowlers.get(bowler).getBowlingSkill() - (double)battingTeamPlayers.get(batsman1).getBattingSkill())/100 : 0.05D;
            double random = Math.random();

            // Factory Design Pattern
            String ballEventType = (random <= wicketProbability) ? "wicketEvent" : "runEvent";
            BallEvent ballEvent = ballEventMapper.get(ballEventType)
                    .setId(UUID.randomUUID().toString())
                    .setMatchID(match.getId())
                    .setBallNumber(ballNumber)
                    .setIsFirstInnings(isFirstInnings)
                    .setBattingTeamId(battingTeamId)
                    .setBowlingTeamId(bowlingTeamId)
                    .setBatsman1Id(battingTeamPlayers.get(batsman1).getId())
                    .setBatsman2Id(battingTeamPlayers.get(batsman2).getId())
                    .setBowlerId(topFiveBowlers.get(bowler).getId());
            ballEvent.setResult(ballEvent.getBallResult());
            ballEventService.save(ballEvent);
            scoreboard.update(ballEvent);
            String ballResult = ballEvent.getResult();
            if(!Objects.equals(ballResult, "W"))
                score += Integer.parseInt(ballResult);
            if(Objects.equals(ballResult, "1") || Objects.equals(ballResult, "3")) {
                int temp = batsman1;
                batsman1 = batsman2;
                batsman2 = temp;
            }
            else if(Objects.equals(ballResult, "W")) {
                batsman1 = Math.max(batsman1, batsman2) + 1;
                if(batsman1 == 11)
                    break;
            }
            if(ballNumber % 6 == 0)
            {
                int temp = batsman1;
                batsman1 = batsman2;
                batsman2 = temp;
                bowler = (bowler+1) % 5; //bowler logic to be changed later.
            }
            if(!isFirstInnings && score >= target)
                break;
        }
        match.setScoreboard(scoreboard);
        return match;
    }

    private List<Player> getTopFiveBowlers(List<Player> bowlingTeamPlayers) {
        bowlingTeamPlayers.sort(Comparator.comparingInt(Player::getBowlingSkill).reversed());
        return bowlingTeamPlayers.subList(0, 5);
    }

    private void getBattingOrderSorted(List<Player> battingTeamPlayers) {
        battingTeamPlayers.sort(Comparator.comparingInt(Player::getBattingSkill).reversed());
    }

    public Match playMatch(Match match) {
        Match matchAfterFirstInnings = playInnings(match, true);
        Match matchAfterSecondInnings = playInnings(matchAfterFirstInnings, false);
        matchAfterSecondInnings.setMatchWonByTeamID(MatchUtils.getWinningTeamId(matchAfterSecondInnings));
        return matchAfterSecondInnings;
    }
    public void saveMatchData(Match match) {
        matchService.save(match);
        playerService.updateTeamPlayers(match.getScoreboard().getTeam1());
        playerService.updateTeamPlayers(match.getScoreboard().getTeam2());
    }
    public Match createAndPlayMatch(MatchRequest matchRequest) {
        validateMatchRequest(matchRequest);
        Match match = createMatch(matchRequest);
        match = playMatch(match);
        saveMatchData(match);
        return match;
    }
    public List<BallEvent> getAllBallEventsInMatch(String matchId)
    {
        return ballEventService.getAllBallEventInMatch(matchId);
    }

}
