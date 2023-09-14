package com.demoproject.cricketapp.service;


import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.custom.exception.NoDataFoundException;
import com.demoproject.cricketapp.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team addTeam(TeamRequest teamRequest) {
        Team team = Team.builder().id(UUID.randomUUID().toString()).teamName(teamRequest.getTeamName()).build();
        teamRepository.save(team);
        return team;
    }

//    public Team getTeamById(String teamId) {
//        Optional<Team> team = teamRepository.findById(teamId);
//
//    }

    public List<TeamInfoResponse> getAllTeamsInfo() {
        List<Team> allTeams = teamRepository.findAll();
        if(allTeams.isEmpty()) {
            throw new NoDataFoundException("No team found. Kindly create a new team.");
        }
        List<TeamInfoResponse> teamInfoResponses = new ArrayList<>();
        for(Team team : allTeams) {
            teamInfoResponses.add(TeamInfoResponse.builder().id(team.getId()).teamName(team.getTeamName()).captainId(team.getCaptainId()).build());
        }
        return teamInfoResponses;
    }

}