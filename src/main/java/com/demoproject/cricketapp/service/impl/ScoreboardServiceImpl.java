package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.service.ScoreboardService;
import com.demoproject.cricketapp.utils.ScoreboardUtils;

import java.util.UUID;

public class ScoreboardServiceImpl implements ScoreboardService {

    @Override
    public Scoreboard createScoreboard(Team team1, Team team2) {
        Team firstTeam = ScoreboardUtils.initialiseTeamForMatch(team1);
        Team secondTeam = ScoreboardUtils.initialiseTeamForMatch(team2);
        return Scoreboard.builder()
                .id(UUID.randomUUID().toString())
                .team1(firstTeam)
                .team2(secondTeam)
                .build();
    }
}
