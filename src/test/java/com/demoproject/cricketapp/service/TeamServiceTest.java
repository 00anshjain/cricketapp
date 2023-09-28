package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.TeamRepository;
import com.demoproject.cricketapp.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamServiceImpl teamService;

    @Test
    void testAddTeam_InvalidTeamRequest() {
        TeamRequest teamRequest = new TeamRequest();
        assertThrows(InvalidUserInputException.class, () -> teamService.addTeam(teamRequest));
        verify(teamRepository, never()).save(any(Team.class));
    }
    @Test
    void testAddTeam_ValidTeamRequest() {
        TeamRequest mockTeamRequest = new TeamRequest();
        String mockTeamName = "testTeam";
        mockTeamRequest.setTeamName(mockTeamName);
        when(teamRepository.save(any(Team.class))).thenAnswer(i -> i.getArguments()[0]);
        Team team = teamService.addTeam(mockTeamRequest);
        assertEquals(team.getTeamName(), mockTeamName);

        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void testAddTeam_InvalidTeam() {
        Team mockTeam = new Team();
        assertThrows(InvalidRequestException.class, () -> teamService.addTeam(mockTeam));
        verify(teamRepository, never()).save(any(Team.class));
    }

    @Test
    void testAddTeam_ValidTeam() {
        String mockTeamId = "testingTeamId";
        String mockTeamName = "testingTeamName";
        Team mockTeam = Team.builder()
                .id(mockTeamId)
                .teamName(mockTeamName)
                .build();
        when(teamRepository.save(mockTeam)).thenReturn(mockTeam);
        teamService.addTeam(mockTeam);

        verify(teamRepository, times(1)).save(mockTeam);
    }

    @Test
    void testGetTeamById_InvalidTeamId() {
        String mockTeamId = "testingTeam";
        when(teamRepository.findById(mockTeamId)).thenReturn(Optional.empty());
        assertThrows(InvalidUserInputException.class, () -> teamService.getTeamById(mockTeamId));

        verify(teamRepository, times(1)).findById(mockTeamId);
    }

    @Test
    void testGetTeamById_ValidTeamId() {
        String mockTeamId = "testingTeamId";
        String mockTeamName = "testingTeamName";
        String player1Id = "testPlayer1Id";
        Player player1 = Player.builder().id(player1Id).build();
        List<Player> mockPlayers = Arrays.asList(player1);
        Team mockTeam = Team.builder()
                .id(mockTeamId)
                .teamName(mockTeamName)
                .players(mockPlayers)
                .captainId(player1Id)
                .build();
        when(teamRepository.findById(mockTeamId)).thenReturn(Optional.of(mockTeam));
        Team team = teamService.getTeamById(mockTeamId);
        assertEquals(team.getId(), mockTeamId);
        assertEquals(team.getTeamName(), mockTeamName);
        assertEquals(team.getPlayers().get(0).getId(), player1Id);
        assertEquals(team.getCaptainId(), player1Id);

        verify(teamRepository, times(1)).findById(mockTeamId);

    }

    @Test
    public void testDropTeam_validId() {
        String mockTeamId = "testingTeamId";
        doNothing().when(teamRepository).deleteById(mockTeamId);
        teamService.dropTeam(mockTeamId);
        verify(teamRepository, times(1)).deleteById(mockTeamId);
    }

    @Test
    public void testGetAllTeamsInfo_NoDataFound() {
        when(teamRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> teamService.getAllTeamsInfo());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllTeams_withDataFromDb() {
        String mockTeamId = "testingTeamId";
        String mockTeamName = "testingTeamName";
        String player1Id = "testPlayer1Id";
        Player player1 = Player.builder().id(player1Id).build();
        List<Player> mockPlayers = Arrays.asList(player1);
        Team mockTeam = Team.builder()
                .id(mockTeamId)
                .teamName(mockTeamName)
                .players(mockPlayers)
                .captainId(player1Id)
                .build();
        List<Team> mockTeams = Arrays.asList(mockTeam);
        when(teamRepository.findAll()).thenReturn(mockTeams);
        List<TeamInfoResponse> teamInfoResponses = teamService.getAllTeamsInfo();
        TeamInfoResponse response = teamInfoResponses.get(0);
        assertEquals(response.getId(), mockTeamId);
        assertEquals(response.getCaptainId(), player1Id);
        assertEquals(response.getTeamName(), mockTeamName);

        verify(teamRepository, times(1)).findAll();
    }
}
