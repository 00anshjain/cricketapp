package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
