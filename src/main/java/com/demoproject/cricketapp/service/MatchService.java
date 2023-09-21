package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;

import java.util.List;



public interface MatchService {
    Match getMatchById(String matchId);

    Scoreboard getMatchScoreboard(String matchId);

    List<MatchInfoResponse> getAllMatches();

    void save(Match match);
}
