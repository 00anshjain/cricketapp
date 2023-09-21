package com.demoproject.cricketapp.facade;


import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PlayerTeamFacade {
    public Player addPlayer(PlayerRequest playerRequest);
    public Player getPlayerById(String playerId);
    public List<PlayerInfoResponse> getAllPlayersInfo();

    // TeamService methods
    public Team addTeam(TeamRequest teamRequest);
    public Team getTeamById(String teamId);
    public List<TeamInfoResponse> getAllTeamsInfo();


    //Player Team methods
    public Team addPlayerToTeam(String teamId, String playerId);
    public Team dropPlayerFromTeam(String teamId, String playerId);

}
