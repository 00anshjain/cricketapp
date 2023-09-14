package com.demoproject.cricketapp.service;


import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<TeamInfoResponse> getAllTeamsInfo() {
        List<Team> allTeams = teamRepository.findAll();
        List<TeamInfoResponse> teamInfoResponses = new ArrayList<>();
        for(Team team : allTeams) {
            teamInfoResponses.add(TeamInfoResponse.builder().id(team.getId()).teamName(team.getTeamName()).captainId(team.getCaptainId()).build());
        }
        return teamInfoResponses;
    }
}