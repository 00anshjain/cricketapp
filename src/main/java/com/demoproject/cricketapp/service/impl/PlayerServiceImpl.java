package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.PlayerRepository;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.utils.PlayerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public Player addPlayer(PlayerRequest playerRequest) {
        Player player = Player.builder()
                .id(UUID.randomUUID().toString())
                .name(playerRequest.getName())
                .age(playerRequest.getAge()).playerType(playerRequest.getPlayerType()).battingSkill(playerRequest.getBattingSkill()).bowlingSkill(playerRequest.getBowlingSkill()).build();
        playerRepository.save(player);
        return player;
    }
    public void addPlayer(Player player) {
        System.out.println("Saving player : " + player);
        playerRepository.save(player);
    }
    public void dropPlayerById(String playerId)
    {
        playerRepository.deleteById(playerId);
    }

    public Player getPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player == null) {
            throw new InvalidUserInputException("No player found for id = " + playerId + ". Kindly check");
        }
        return player;
    }

    public List<PlayerInfoResponse> getAllPlayersInfo() {
        List<PlayerInfoResponse> playerInfoResponses = new ArrayList<>();
        List<Player> allPlayers = playerRepository.findAll();
        if(allPlayers.isEmpty()) {
            throw new NoDataFoundException("No player found. Kindly add players first");
        }
        for(Player player : allPlayers) {
            playerInfoResponses.add(PlayerInfoResponse.builder().id(player.getId()).name(player.getName()).age(player.getAge()).numberOfMatches(player.getNumberOfMatches()).totalRuns(player.getTotalRuns()).totalWickets(player.getTotalWickets()).playerType(player.getPlayerType()).battingSkill(player.getBattingSkill()).bowlingSkill(player.getBowlingSkill()).build());
        }
        return playerInfoResponses;
    }
    public void updateTeamPlayers(Team team)
    {
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            String playerId = player.getId();
            Player playerFromDb = getPlayerById(playerId);
            player = PlayerUtils.mergePlayerStats(playerFromDb, player);
            dropPlayerById(playerId);
            addPlayer(player);
        }
    }
}
