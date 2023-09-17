package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.MatchRepository;
import com.demoproject.cricketapp.service.MatchService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<MatchInfoResponse> getAllMatches() {
        List<Match> allMatches = matchRepository.findAll();
        if(allMatches.isEmpty()) {
            throw new NoDataFoundException("No match found in database.");
        }
        List<MatchInfoResponse> matchInfoResponses = new ArrayList<>();
        for(Match match :allMatches) {
            matchInfoResponses.add(MatchInfoResponse.builder()
                    .id(match.getId())
                    .dateTime(match.getDateTime())
                    .scoreboard(match.getScoreboard())
                    .matchWonByTeamID(match.getMatchWonByTeamID())
                    .build());
        }
        return matchInfoResponses;
    }
}
