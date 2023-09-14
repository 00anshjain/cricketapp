package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.demoproject.cricketapp.beans.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlayerTeamService {
    private final PlayerService playerService;
    private final TeamService teamService;

    public int getPlayerPositionInTeam(Team team, String playerId) {
        List<Player> players = team.getPlayers();
        if(players == null || players.isEmpty())
            return -1;
        for(int i = 0; i < players.size(); i++)
        {
            if(Objects.equals(players.get(i).getId(), playerId)) {
                return i;
            }
        }
        return -1;
    }
    public Team addPlayerToTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        // Exception for team not found handled in getTeamById method TeamService class.
        Player player = playerService.getPlayerById(playerId);
        // Exception for player not found handled in getPlayerById method in Player class.
        if(getPlayerPositionInTeam(team, playerId) != -1)
            throw new InvalidUserInputException("Player already exists in teams. Kindly check playerId and teamId");
        List<Player> players = team.getPlayers();
        if(players == null)
            players = new ArrayList<Player>();
        players.add(player);
        team.setPlayers(players);
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }

    public Team dropPlayerFromTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        // Exception for team not found handled in getTeamById method TeamService class.
        Player player = playerService.getPlayerById(playerId);
        // Exception for player not found handled in getPlayerById method in Player class.
        int index = getPlayerPositionInTeam(team, playerId);
        if(index == -1)
            throw new InvalidUserInputException("Player doesn't exists in teams. Cannot drop player from team");
        List<Player> players = team.getPlayers();
        players.remove(index);
        team.setPlayers(players);
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }


}
