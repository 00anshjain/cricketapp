package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player addPlayer(PlayerRequest playerRequest);
    void addPlayer(Player player);
    void dropPlayerById(String playerId);
    Player getPlayerById(String playerId);

    List<PlayerInfoResponse> getAllPlayersInfo();

    void updateTeamPlayers(Team team);
}

