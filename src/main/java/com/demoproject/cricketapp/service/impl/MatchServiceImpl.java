package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.MatchRepository;
import com.demoproject.cricketapp.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                    .team1Id(getMatchScoreboard(match.getId()) .getTeam1().getId())
                    .team2Id(getMatchScoreboard(match.getId()) .getTeam2().getId())
                    .matchWonByTeamID(match.getMatchWonByTeamID())
                    .build());
        }
        return matchInfoResponses;
    }
    public void save(Match match) {
        if(match.getId() == null || match.getId().isEmpty())
            throw new InvalidRequestException("Match must have an ID.");
        matchRepository.save(match);
    }
}
