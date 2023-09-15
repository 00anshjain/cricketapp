package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.facade.PlayerTeamFacade;
import com.demoproject.cricketapp.service.MatchService;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.service.TeamService;
import com.demoproject.cricketapp.utils.TeamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class PlayerTeamFacadeImpl implements PlayerTeamFacade {
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

    //Team-player Services
    public Team addPlayerToTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        // Exception for team not found handled in getTeamById method TeamService class.
        Player player = playerService.getPlayerById(playerId);
        // Exception for player not found handled in getPlayerById method in Player class.
        if(TeamUtils.getPlayerPositionInTeam(team, playerId) != -1)
            throw new InvalidUserInputException("Player already exists in teams. Kindly check playerId and teamId");
        List<Player> players = team.getPlayers();
        if(players == null)
            players = new ArrayList<Player>();

        players.add(player);
        team.setPlayers(players);
        team.setCaptainId(playerId); //To be removed later
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }

    public Team dropPlayerFromTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        // Exception for team not found handled in getTeamById method TeamService class.
        Player player = playerService.getPlayerById(playerId);
        // Exception for player not found handled in getPlayerById method in Player class.
        int index = TeamUtils.getPlayerPositionInTeam(team, playerId);
        if (index == -1)
            throw new InvalidUserInputException("Player doesn't exists in teams. Cannot drop player from team");
        List<Player> players = team.getPlayers();
        if (Objects.equals(team.getCaptainId(), players.get(index).getId()))
            team.setCaptainId("");
        players.remove(index);
        team.setPlayers(players);
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }

}
