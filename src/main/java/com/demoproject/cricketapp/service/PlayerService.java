package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;

import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Player addPlayer(PlayerRequest playerRequest) {
        Player player = Player.builder()
                .id(UUID.randomUUID().toString())
                .name(playerRequest.getName())
                .age(playerRequest.getAge()).playerType(playerRequest.getPlayerType()).battingSkill(playerRequest.getBattingSkill()).bowlingSkill(playerRequest.getBowlingSkill()).build();
        playerRepository.save(player);
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
}
