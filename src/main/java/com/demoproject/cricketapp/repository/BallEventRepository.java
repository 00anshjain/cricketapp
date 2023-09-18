package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.BallEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallEventRepository extends MongoRepository<BallEvent, String> {

}
