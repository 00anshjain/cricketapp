package com.demoproject.cricketapp.repository.impl;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Player save(Player player) {
        return mongoTemplate.save(player);
    }

    @Override
    public void deleteById(String playerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(playerId));
        mongoTemplate.findAndRemove(query, Player.class);
    }

    @Override
    public Optional<Player> findById(String playerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(playerId));
        List<Player> players = mongoTemplate.find(query, Player.class);
        if(players.isEmpty())
            return Optional.empty();
        return Optional.of(players.get(0));
    }

    @Override
    public List<Player> findAll() {
        Query query = new Query();
        return mongoTemplate.find(query, Player.class);
    }
}
