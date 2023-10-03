package com.demoproject.cricketapp.repository;


import com.demoproject.cricketapp.beans.Team;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface TeamRepository extends MongoRepository<Team, String> {
//
//}
public interface TeamRepository {

    Optional<Team> findById(String teamId);

    List<Team> findAll();

    void deleteById(String teamId);

    Team save(Team team);
}