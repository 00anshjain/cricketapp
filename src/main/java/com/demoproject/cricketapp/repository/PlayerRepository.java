package com.demoproject.cricketapp.repository;

import com.demoproject.cricketapp.beans.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{

}
