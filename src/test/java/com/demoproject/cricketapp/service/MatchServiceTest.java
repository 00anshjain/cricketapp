package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.MatchRepository;
import com.demoproject.cricketapp.service.impl.MatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void testGetMatchById_NotFound() {
        String matchId = "testingId";
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> matchService.getMatchById(matchId));

        verify(matchRepository, times(1)).findById(matchId);
    }

    @Test
    void testGetMatchById_Success() {
        String matchId = "testingId";
        Match mockMatch = Match.builder().id(matchId).build();
        when(matchRepository.findById(matchId)).thenReturn(Optional.of(mockMatch));
        Match match = matchService.getMatchById(matchId);
        assertNotNull(match);
        assertEquals(match.getId(), mockMatch.getId());

        verify(matchRepository, times(1)).findById(matchId);

    }

    @Test
    void testGetMatchScoreboard_InvalidMatchId() {
        String matchId = "testingId";
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> matchService.getMatchScoreboard(matchId));

        verify(matchRepository, times(1)).findById(matchId);
    }

    @Test
    void testGetMatchScoreboard_Success() {
        String mockScoreboardId = "testingScoreboardId";
        String mockMatchId = "testingMatchId";
        Team mockTeam1 = Team.builder().id("testingTeam1").build();
        Team mockTeam2 = Team.builder().id("testingTeam2").build();
        Scoreboard mockScoreboard = Scoreboard.builder().id(mockScoreboardId).team1(mockTeam1).team2(mockTeam2).build();
        Match mockMatch = Match.builder().id(mockMatchId).scoreboard(mockScoreboard).build();
        when(matchRepository.findById(mockMatchId)).thenReturn(Optional.of(mockMatch));
        Scoreboard scoreboard = matchService.getMatchScoreboard(mockMatchId);
        assertNotNull(scoreboard);
        assertEquals(scoreboard.getId(), mockScoreboardId);
        assertEquals(scoreboard.getTeam1(), mockTeam1);
        assertEquals(scoreboard.getTeam2(), mockTeam2);

        verify(matchRepository, times(1)).findById(mockMatchId);

    }

    @Test
    void testGetAllMatches_NoDataFound() {
        when(matchRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> matchService.getAllMatches());

        verify(matchRepository, times(1)).findAll();
    }

    @Test
    void testGetAllMatches_WithDataFromDb() {
        String mockScoreboardId = "testingScoreboardId";
        String mockMatchId = "testingMatchId";
        String mockTeam1Id = "testingTeam1";
        String mockTeam2Id = "testingTeam2";
        String mockMatchWonByTeamId = "testingTeam1";
        LocalDateTime mockDateTime = LocalDateTime.now();
        Team mockTeam1 = Team.builder().id(mockTeam1Id).build();
        Team mockTeam2 = Team.builder().id(mockTeam2Id).build();
        Scoreboard scoreboard = Scoreboard.builder()
                .id(mockScoreboardId)
                .team1(mockTeam1)
                .team2(mockTeam2)
                .build();
        Match mockMatch = Match.builder()
                .id(mockMatchId)
                .dateTime(mockDateTime)
                .scoreboard(scoreboard)
                .matchWonByTeamID(mockMatchWonByTeamId)
                .build();
        when(matchRepository.findAll()).thenReturn(Arrays.asList(mockMatch));
        when(matchRepository.findById(mockMatchId)).thenReturn(Optional.ofNullable(mockMatch));
        List<MatchInfoResponse> matchInfoResponses = matchService.getAllMatches();
        assertNotNull(matchInfoResponses);
        MatchInfoResponse response = matchInfoResponses.get(0);
        assertEquals(mockMatchId, response.getId());
        assertEquals(mockTeam1Id, response.getTeam1Id());
        assertEquals(mockTeam2Id, response.getTeam2Id());
        assertEquals(mockMatchWonByTeamId, response.getMatchWonByTeamID());
        assertEquals(mockDateTime, response.getDateTime());

        verify(matchRepository, times(1)).findAll();
        verify(matchRepository, times(2)).findById(mockMatchId);
    }

    @Test
    void testSave_InvalidMatch()
    {
        Match mockMatch = new Match();
        assertThrows(InvalidRequestException.class, () -> matchService.save(mockMatch));
        verify(matchRepository, times(0)).save(mockMatch);
    }

    @Test
    void testSave_Success()
    {
        String mockMatchId = "testingMatchId";
        Match mockMatch = Match.builder()
                .id(mockMatchId)
                .build();
        when(matchRepository.save(mockMatch)).thenReturn(mockMatch);
        matchService.save(mockMatch);
        verify(matchRepository, times(1)).save(mockMatch);
    }

}
