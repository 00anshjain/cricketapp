package com.demoproject.cricketapp.facade;


import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;

import java.util.List;

public interface CricketServicesFacade {
    public Player addPlayer(PlayerRequest playerRequest);
    public Player getPlayerById(String playerId);
    public List<PlayerInfoResponse> getAllPlayersInfo();

    // TeamService methods
    public Team addTeam(TeamRequest teamRequest);
    public Team getTeamById(String teamId);
    public List<TeamInfoResponse> getAllTeamsInfo();

    // MatchService methods
    public Match getMatchById(String matchId);

    public Scoreboard getMatchScoreboard(String matchId);
}
