package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import org.springframework.stereotype.Service;


@Service
public interface MatchService {
    Match getMatchById(String matchId);

    Scoreboard getMatchScoreboard(String matchId);
}
