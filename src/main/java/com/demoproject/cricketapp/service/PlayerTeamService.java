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

    public boolean checkPlayerInTeam(Team team, String playerId) {
        List<Player> players = team.getPlayers();
        if(players == null || players.isEmpty())
            return false;
        for(Player player : players) {
            if(Objects.equals(player.getId(), playerId)) {
                return true;
            }
        }
        return false;
    }
    public Team addPlayerToTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        // Exception for team not found handled in getTeamById method TeamService class.
        if(checkPlayerInTeam(team, playerId))
            throw new InvalidUserInputException("Player already exists in teams. Kindly check playerId and teamId");
        List<Player> players = team.getPlayers();
        if(players == null)
            players = new ArrayList<Player>();
        Player player = playerService.getPlayerById(playerId);
        // Exception for player not found handled in getPlayerById method in Player class.
        players.add(player);
        team.setPlayers(players);
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }


}
