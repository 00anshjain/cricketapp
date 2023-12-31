package com.demoproject.cricketapp.facade;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.RunEvent;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.WicketEvent;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public interface MatchFacade {

    Map<String, BallEvent> ballEventMapper = Maps.newHashMap(
            Map.of("wicketEvent", new WicketEvent(), "runEvent", new RunEvent())
    );

    // MatchService methods
    public Match getMatchById(String matchId);
    public Scoreboard getMatchScoreboard(String matchId);
    List<MatchInfoResponse> getAllMatches();
    Match createMatch(MatchRequest matchRequest);
    Match createAndPlayMatch(MatchRequest matchRequest);
    void validateMatchRequest(MatchRequest matchRequest);
    Match playInnings(Match match, Boolean isFirstInnings);
    Match playMatch(Match match);
    void saveMatchData(Match match);
    List<BallEvent> getAllBallEventsInMatch(String matchId);
}
