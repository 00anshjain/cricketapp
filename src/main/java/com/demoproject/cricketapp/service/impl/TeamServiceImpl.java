package com.demoproject.cricketapp.service.impl;


import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.TeamRepository;
import com.demoproject.cricketapp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public Team addTeam(TeamRequest teamRequest) {
        if(teamRequest == null || teamRequest.getTeamName() == null || teamRequest.getTeamName().isEmpty())
            throw new InvalidUserInputException("Necessary parameters required for team creation are not given. Kindly check");
        Team team = Team.builder().id(UUID.randomUUID().toString()).teamName(teamRequest.getTeamName()).build();
        teamRepository.save(team);
        return team;
    }

    public Team addTeam(Team team) {
        teamRepository.save(team);
        return team;
    }


    public Team getTeamById(String teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            throw new InvalidUserInputException("No team found for id = " + teamId + ". Kindly check");
        }
        return team;
    }

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

    public void dropTeam(String teamId) {
        teamRepository.deleteById(teamId);
    }
}