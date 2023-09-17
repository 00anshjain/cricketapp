package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import org.springframework.stereotype.Service;

@Service
public interface ScoreboardService {
    Scoreboard createScoreboard(Team team1, Team team2);
}
