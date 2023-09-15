package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;



@UtilityClass
public class TeamUtils {
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
}
