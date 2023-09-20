package com.demoproject.cricketapp.repository.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Match save(Match match) {
        mongoTemplate.save(match);
        return match;
    }

    @Override
    public List<Match> findAll() {
//        return mongoTemplate.findAll(Match.class, "matches");
        Query query = new Query();
        return mongoTemplate.find(query, Match.class);
    }


    @Override
    public Optional<Match> findById(String matchId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(matchId));
        List<Match> matches = mongoTemplate.find(query, Match.class);
        if(matches.isEmpty())
            return Optional.empty();
        return Optional.of(matches.get(0));
    }
}
