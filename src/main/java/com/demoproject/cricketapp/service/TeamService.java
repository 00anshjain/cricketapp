package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;

import java.util.List;


public interface TeamService {
    Team addTeam(TeamRequest teamRequest);

    Team addTeam(Team team);

    Team getTeamById(String teamId);

    List<TeamInfoResponse> getAllTeamsInfo();

    void dropTeam(String teamId);
}

