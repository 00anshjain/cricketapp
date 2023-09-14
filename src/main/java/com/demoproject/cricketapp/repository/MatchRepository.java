package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match, String>{

}
