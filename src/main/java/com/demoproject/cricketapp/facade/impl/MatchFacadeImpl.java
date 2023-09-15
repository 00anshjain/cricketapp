package com.demoproject.cricketapp.facade.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.facade.MatchFacade;
import com.demoproject.cricketapp.service.MatchService;
import com.demoproject.cricketapp.service.PlayerService;
import com.demoproject.cricketapp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MatchFacadeImpl implements MatchFacade {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MatchService matchService;

    // MatchService methods
    public Match getMatchById(String matchId) {
        return matchService.getMatchById(matchId);
    }

    public Scoreboard getMatchScoreboard(String matchId) {
        return matchService.getMatchScoreboard(matchId);
    }

}
