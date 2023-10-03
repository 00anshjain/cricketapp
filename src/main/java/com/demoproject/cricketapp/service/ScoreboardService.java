package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;


public interface ScoreboardService {
    Scoreboard createScoreboard(Team team1, Team team2);
}
