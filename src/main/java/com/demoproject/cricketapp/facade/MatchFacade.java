package com.demoproject.cricketapp.facade;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import org.springframework.stereotype.Component;

@Component
public interface MatchFacade {
    // MatchService methods
    public Match getMatchById(String matchId);

    public Scoreboard getMatchScoreboard(String matchId);
}
