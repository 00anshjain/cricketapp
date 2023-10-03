package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.BallEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface BallEventRepository extends MongoRepository<BallEvent, String> {
//
//    @Query("{ 'matchID' : ?0 }")
//    List<BallEvent> findAllByMatchId(String matchId);
//}

@Repository
public interface BallEventRepository {
    void save(BallEvent ballEvent);

    List<BallEvent> findAllByMatchId(String matchId);
}
