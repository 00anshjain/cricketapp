package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.MatchRepository;
import com.demoproject.cricketapp.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    public Match getMatchById(String matchId) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match == null) {
            throw new NoDataFoundException("No match found for id = " + matchId + ". Kindly check");
        }
        return match;
    }

    public Scoreboard getMatchScoreboard(String matchId)
    {
        Match match = getMatchById(matchId);
        return match.getScoreboard();
    }


}
