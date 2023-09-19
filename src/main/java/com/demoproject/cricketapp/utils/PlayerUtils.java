package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.commons.enums.PlayerType;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
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
        mergedPlayer.setNumberOfMatches(player1.getNumberOfMatches() + player2.getNumberOfMatches());
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

    public static void validatePlayerRequest(PlayerRequest playerRequest) {
        if (playerRequest == null) {
            throw new InvalidUserInputException("Player request cannot be null");
        }
        if (playerRequest.getName() == null || playerRequest.getName().isEmpty()) {
            throw new InvalidUserInputException("Player name is required");
        }
        if (playerRequest.getAge() <= 0) {
            throw new InvalidUserInputException("Player age must be greater than 0");
        }
        if (playerRequest.getPlayerType() == null || !isValidPlayerType(playerRequest.getPlayerType())) {
            throw new InvalidUserInputException("Invalid player type");
        }
        if (playerRequest.getBattingSkill() < 0 || playerRequest.getBattingSkill() > 100) {
            throw new InvalidUserInputException("Batting skill must be between 0 and 100");
        }
        if (playerRequest.getBowlingSkill() < 0 || playerRequest.getBowlingSkill() > 100) {
            throw new InvalidUserInputException("Bowling skill must be between 0 and 100");
        }
    }
    private static boolean isValidPlayerType(PlayerType playerType) {
        for (PlayerType validType : PlayerType.values()) {
            if (validType == playerType) {
                return true;
            }
        }
        return false;
    }
}
