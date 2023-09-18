package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerUtils {
//    public Player mergePlayerStats(Player player1, Player player2) {
//
//    }
    public Player mergePlayerStats(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new NoDataFoundException("Both players must be non-null.");
        }
        Player mergedPlayer = new Player();
        mergedPlayer.setId(player1.getId());
        mergedPlayer.setName(player1.getName());
        mergedPlayer.setAge(player1.getAge());
        mergedPlayer.setNumberOfMatches(player1.getNumberOfMatches());
        mergedPlayer.setTotalRuns(player1.getTotalRuns() + player2.getTotalRuns());
        mergedPlayer.setCenturies(player1.getCenturies() + player2.getCenturies());
        mergedPlayer.setHighestScore(Math.max(player1.getHighestScore(), player2.getHighestScore()));
        mergedPlayer.setTotalWickets(player1.getTotalWickets() + player2.getTotalWickets());
        mergedPlayer.setMostWickets(Math.max(player1.getMostWickets(), player2.getMostWickets()));
        mergedPlayer.setPlayerType(player1.getPlayerType());
        mergedPlayer.setBattingSkill(player1.getBattingSkill());
        mergedPlayer.setBowlingSkill(player1.getBowlingSkill());

        return mergedPlayer;
    }

}
