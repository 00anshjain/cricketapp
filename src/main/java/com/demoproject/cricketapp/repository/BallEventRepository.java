package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.BallEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallEventRepository extends MongoRepository<BallEvent, String> {

    @Query("{ 'matchID' : ?0 }")
    List<BallEvent> findAllByMatchId(String matchId);
}
