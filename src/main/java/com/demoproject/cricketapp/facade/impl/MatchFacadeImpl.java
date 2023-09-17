package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.Toss;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.facade.MatchFacade;
import com.demoproject.cricketapp.service.MatchService;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.service.ScoreboardService;
import com.demoproject.cricketapp.service.TeamService;
import com.demoproject.cricketapp.utils.MatchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class MatchFacadeImpl implements MatchFacade {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MatchService matchService;
    private final ScoreboardService scoreboardService;

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

    public Match playMatch(MatchRequest matchRequest) {
        Team team1 = teamService.getTeamById(matchRequest.getTeam1Id());
        Team team2 = teamService.getTeamById(matchRequest.getTeam2Id());
        MatchUtils.validateTeamForMatch(team1);
        MatchUtils.validateTeamForMatch(team2);
        Scoreboard scoreboard = scoreboardService.createScoreboard(team1, team2);
        Toss toss = MatchUtils.haveToss(team1, team2);

        return Match.builder()
                .id(UUID.randomUUID().toString())
                .toss(toss)
                .overs(matchRequest.getOvers())
                .dateTime(matchRequest.getDateTime())
                .scoreboard(scoreboard).build();
    }
}
