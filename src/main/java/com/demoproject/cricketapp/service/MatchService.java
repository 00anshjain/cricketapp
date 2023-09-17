package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MatchService {
    Match getMatchById(String matchId);

    Scoreboard getMatchScoreboard(String matchId);

    List<MatchInfoResponse> getAllMatches();
}
