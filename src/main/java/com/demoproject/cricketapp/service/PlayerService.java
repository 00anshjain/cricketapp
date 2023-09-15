package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player addPlayer(PlayerRequest playerRequest);

    Player getPlayerById(String playerId);

    List<PlayerInfoResponse> getAllPlayersInfo();
}

