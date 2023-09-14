package com.demoproject.cricketapp.repository;


import com.demoproject.cricketapp.beans.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

}
