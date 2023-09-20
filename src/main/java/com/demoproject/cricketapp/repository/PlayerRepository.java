package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {

    void save(Player player);

    void deleteById(String playerId);

    Optional<Player> findById(String playerId);

    List<Player> findAll();
}
