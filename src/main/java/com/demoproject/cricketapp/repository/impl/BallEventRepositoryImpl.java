package com.demoproject.cricketapp.repository.impl;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.repository.BallEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BallEventRepositoryImpl implements BallEventRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(BallEvent ballEvent) {
        mongoTemplate.save(ballEvent);
    }

    @Override
    public List<BallEvent> findAllByMatchId(String matchId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("matchID").is(matchId));
//        query.with(Sort.by(Sort.Direction.ASC, "age"));
        query.with(Sort.by(Sort.Direction.DESC, "isFirstInnings"));
        query.with(Sort.by(Sort.Direction.ASC, "ballNumber"));
        return mongoTemplate.find(query, BallEvent.class);
    }
}
