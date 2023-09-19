package com.demoproject.cricketapp.facade;

import com.demoproject.cricketapp.beans.*;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
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
