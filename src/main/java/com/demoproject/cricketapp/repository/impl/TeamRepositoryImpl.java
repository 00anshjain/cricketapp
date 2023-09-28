package com.demoproject.cricketapp.repository.impl;

import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Team> findById(String teamId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(teamId));
        List<Team> teams = mongoTemplate.find(query, Team.class);
        if(teams.isEmpty())
            return Optional.empty();
        return Optional.of(teams.get(0));
    }

    @Override
    public List<Team> findAll() {
        Query query = new Query();
        return mongoTemplate.find(query, Team.class);
    }

    @Override
    public void deleteById(String teamId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(teamId));
        mongoTemplate.findAndRemove(query, Team.class);
    }

    @Override
    public Team save(Team team) {
        return mongoTemplate.save(team);
    }
}
