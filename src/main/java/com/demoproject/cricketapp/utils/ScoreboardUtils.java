package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.springframework.beans.BeanUtils.copyProperties;


@UtilityClass
public class ScoreboardUtils {

    public static Team initialiseTeamForMatch(Team team) {
        Team teamClone = new Team();
        copyProperties(team, teamClone);
        List<Player> players = teamClone.getPlayers();
        for(int i = 0; i < 11; i++) {
            Player player = players.get(i);
            player.setNumberOfMatches(1);
            player.setTotalRuns(0);
            player.setTotalWickets(0);
            player.setHighestScore(0);
            player.setMostWickets(0);
            player.setCenturies(0);
            players.set(i, player);
        }
        teamClone.setPlayers(players);
        return teamClone;
    }

    public static Team updateBowlerWicket(Team team, String playerId) {
        List<Player> players= team.getPlayers();
        for(int playersIterator =0; playersIterator < 11; playersIterator++)
        {
            Player player = players.get(playersIterator);
            if(Objects.equals(player.getId(), playerId))
            {
                int newTotalWickets = player.getTotalWickets() + 1;
                player.setTotalWickets(newTotalWickets+1);
                player.setMostWickets(newTotalWickets);
                players.set(playersIterator, player);
                break;
            }
        }
        team.setPlayers(players);
        return team;
    }

    public static Team updateBatsmanRun(Team team, String playerId, String run) {
        List<Player> players= team.getPlayers();
        for(int playersIterator = 0; playersIterator < 11; playersIterator++)
        {
            Player player = players.get(playersIterator);
            if(Objects.equals(player.getId(), playerId))
            {
                int newTotalRuns = player.getTotalRuns() + Integer.parseInt(run);
                player.setTotalRuns(newTotalRuns);
                player.setHighestScore(newTotalRuns);
                if(newTotalRuns >= 100 && player.getCenturies() == 0)
                    player.setCenturies(1);
                players.set(playersIterator, player);
                break;
            }
        }
        team.setPlayers(players);
        return team;
    }
}
