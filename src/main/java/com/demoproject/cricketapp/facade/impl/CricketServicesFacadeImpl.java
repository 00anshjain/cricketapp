package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.facade.CricketServicesFacade;
import com.demoproject.cricketapp.service.MatchService;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CricketServicesFacadeImpl implements CricketServicesFacade {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MatchService matchService;

//     PlayerService methods
    public Player addPlayer(PlayerRequest playerRequest) {
        return playerService.addPlayer(playerRequest);
    }

    public Player getPlayerById(String playerId) {
        return playerService.getPlayerById(playerId);
    }

    public List<PlayerInfoResponse> getAllPlayersInfo() {
        return playerService.getAllPlayersInfo();
    }

    // TeamService methods
    public Team addTeam(TeamRequest teamRequest) {
        return teamService.addTeam(teamRequest);
    }

    public Team getTeamById(String teamId) {
        return teamService.getTeamById(teamId);
    }

    public List<TeamInfoResponse> getAllTeamsInfo() {
        return teamService.getAllTeamsInfo();
    }

    // MatchService methods
    public Match getMatchById(String matchId) {
        return matchService.getMatchById(matchId);
    }

    public Scoreboard getMatchScoreboard(String matchId) {
        return matchService.getMatchScoreboard(matchId);
    }
}
