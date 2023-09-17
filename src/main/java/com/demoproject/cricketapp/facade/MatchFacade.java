package com.demoproject.cricketapp.facade;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MatchFacade {
    // MatchService methods
    public Match getMatchById(String matchId);

    public Scoreboard getMatchScoreboard(String matchId);

    List<MatchInfoResponse> getAllMatches();

    Match createAndPlayMatch(MatchRequest matchRequest);
}
