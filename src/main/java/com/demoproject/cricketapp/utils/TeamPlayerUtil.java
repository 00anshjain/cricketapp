package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.demoproject.cricketapp.beans.Team;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TeamPlayerUtil {
    private final PlayerService playerService;
    private final TeamService teamService;
    public Team addPlayerToTeam(String teamId, String playerId) {
        Team team = teamService.getTeamById(teamId);
        if(checkPlayerInTeam(team, playerId))
            throw new InvalidUserInputException("Player already exists in teams. Kindly check playerId and teamId");
        List<Player> players = team.getPlayers();
        Player player = playerService.getPlayerById(playerId);
        players.add(player);
        team.setPlayers(players);
        teamService.dropTeam(team.getId());
        return teamService.addTeam(team);
    }
    public boolean checkPlayerInTeam(Team team, String playerId) {
        for(Player player : team.getPlayers()) {
            if(Objects.equals(player.getId(), playerId)) {
                return true;
            }
        }
        return false;
    }
}
