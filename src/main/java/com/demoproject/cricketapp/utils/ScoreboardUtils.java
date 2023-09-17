package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;


@UtilityClass
public class ScoreboardUtils {

    public static Team initialiseTeamForMatch(Team team) {
        Team teamClone = new Team();
        copyProperties(team, teamClone);
        List<Player> players = teamClone.getPlayers();
        for(int i = 0; i < 11; i++) {
            players.get(i).setTotalRuns(0);
            players.get(i).setTotalRuns(0);
        }
        teamClone.setPlayers(players);
        return teamClone;
    }

}
