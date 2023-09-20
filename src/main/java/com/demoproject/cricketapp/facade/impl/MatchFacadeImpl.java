package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.*;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidMatchRequestException;
import com.demoproject.cricketapp.facade.MatchFacade;
import com.demoproject.cricketapp.service.*;
import com.demoproject.cricketapp.utils.MatchUtils;
import com.demoproject.cricketapp.utils.TeamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


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
        validateNoCommonPlayer(team1, team2);
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
    private void validateNoCommonPlayer(Team team1, Team team2) {
        List<Player> playersTeam1 = team1.getPlayers();
        List<Player> playersTeam2 = team2.getPlayers();
        for(Player player : playersTeam1) {
            if(TeamUtils.getPlayerPositionInTeam(team2, player.getId()) != -1)
                throw new InvalidMatchRequestException("Invalid match request. Both them contains a common player");
        }
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
        MatchUtils.getBattingOrderSorted(battingTeamPlayers);
        List<Player> topFiveBowlers = MatchUtils.getTopFiveBowlers(bowlingTeamPlayers);

        long maximumBalls = match.getOvers() * 6L;
        Map<String, Integer> currentPlayers = MatchUtils.initialiseCurrentPlayers();
        int inningsScore = 0, battingTeamSize = battingTeamPlayers.size();
        for(long ballNumber = 1; ballNumber <= maximumBalls; ballNumber++)
        {
            int batsman1 = currentPlayers.get("batsman1");
            int batsman2 = currentPlayers.get("batsman2");
            if((batsman1 == battingTeamSize) || (batsman2 == battingTeamSize))
                break;
            int bowler = currentPlayers.get("bowler");
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
            inningsScore = MatchUtils.updateInningsScore(inningsScore, ballEvent);
            MatchUtils.updateCurrentPlayers(currentPlayers, ballEvent);

            if(!isFirstInnings && inningsScore >= target)
                break;
        }
        match.setScoreboard(scoreboard);
        return match;
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
        getMatchById(matchId);
        return ballEventService.getAllBallEventInMatch(matchId);
    }

}
