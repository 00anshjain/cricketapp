package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.Match;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface MatchRepository extends MongoRepository<Match, String>{
//
//}


public interface MatchRepository {
    Match save(Match match);
    List<Match> findAll();
    Optional<Match> findById(String matchId);


}