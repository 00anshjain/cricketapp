package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.MatchRepository;
import com.demoproject.cricketapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchService {
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
