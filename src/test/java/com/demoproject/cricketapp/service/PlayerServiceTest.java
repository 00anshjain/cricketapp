package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.commons.enums.PlayerType;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import com.demoproject.cricketapp.repository.PlayerRepository;
import com.demoproject.cricketapp.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void testAddPlayer_InvalidPlayerRequest() {
        PlayerRequest playerRequest = new PlayerRequest();
        assertThrows(InvalidRequestException.class, () -> playerService.addPlayer(playerRequest));

        verify(playerRepository, never()).save(any(Player.class)); //To verify that this method is never called
    }

    @Test
    void testAddPlayer_ValidPlayerRequest() {
        PlayerRequest mockPlayerRequest = new PlayerRequest();
        mockPlayerRequest.setName("testingPlayer");
        mockPlayerRequest.setAge(25);
        mockPlayerRequest.setPlayerType(PlayerType.BATSMAN);
        mockPlayerRequest.setBattingSkill(60);
        mockPlayerRequest.setBowlingSkill(55);

        Player mockPlayer = Player.builder()
                .id(UUID.randomUUID().toString())
                .name(mockPlayerRequest.getName())
                .age(mockPlayerRequest.getAge())
                .playerType(mockPlayerRequest.getPlayerType())
                .battingSkill(mockPlayerRequest.getBattingSkill())
                .bowlingSkill(mockPlayerRequest.getBowlingSkill())
                .build();
        //thenAnswer to return parameter which was input.
        when(playerRepository.save(any(Player.class))).thenAnswer(i -> i.getArguments()[0]);
        Player player = playerService.addPlayer(mockPlayerRequest);
        assertEquals(player.getName(), mockPlayer.getName());
        assertEquals(player.getAge(), mockPlayer.getAge());
        assertEquals(player.getPlayerType(), mockPlayer.getPlayerType());
        assertEquals(player.getBattingSkill(), mockPlayer.getBattingSkill());
        assertEquals(player.getBowlingSkill(), mockPlayer.getBowlingSkill());

        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void testAddPlayer_InvalidPlayer() {
        Player player = new Player();
        assertThrows(InvalidRequestException.class, () -> playerService.addPlayer(player));

        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    void testAddPlayer_PlayerObject() {
        Player player = Player.builder()
                .id("1")
                .name("testingPlayer")
                .age(5)
                .playerType(PlayerType.BATSMAN)
                .battingSkill(50)
                .bowlingSkill(30)
                .build();
        when(playerRepository.save(player)).thenReturn(player);
        playerService.addPlayer(player);

        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testDropPlayerById_validId() {
        String playerId = "1";
        doNothing().when(playerRepository).deleteById(playerId);
        playerService.dropPlayerById(playerId);

        verify(playerRepository, times(1)).deleteById(playerId);
    }

    @Test
    public void testGetPlayerById_ValidId() {
        String mockPlayerId = "1", mockPlayerName = "testPlayer";
        int mockPlayerAge = 5;
        Player mockPlayer = Player.builder()
                .id(mockPlayerId)
                .name(mockPlayerName)
                .age(mockPlayerAge)
                .build();
        when(playerRepository.findById(mockPlayerId)).thenReturn(Optional.of(mockPlayer));
        Player player = playerService.getPlayerById(mockPlayerId);
        assertNotNull(player);
        assertEquals(mockPlayerId, player.getId());
        assertEquals(mockPlayerName, player.getName());
        assertEquals(mockPlayerAge, player.getAge());

        verify(playerRepository, times(1)).findById(mockPlayerId);
    }

    @Test
    public void testGetPlayerById_InvalidId() {
        String mockPlayerId = "testInvalidId";
        when(playerRepository.findById(mockPlayerId)).thenReturn(Optional.empty());
        assertThrows(InvalidUserInputException.class, () -> playerService.getPlayerById(mockPlayerId));

        verify(playerRepository, times(1)).findById(mockPlayerId);
    }

    @Test
    public void testGetAllPlayersInfo_NoDataFound() {
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> playerService.getAllPlayersInfo());
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllPlayersInfo_WithDataFromDb() {
        Player player = Player.builder()
                .id("1")
                .name("testingPlayer")
                .age(5)
                .playerType(PlayerType.BATSMAN)
                .battingSkill(50)
                .bowlingSkill(55)
                .build();
        List<Player> mockPlayers = Arrays.asList(player);
        when(playerRepository.findAll()).thenReturn(mockPlayers);
        List<PlayerInfoResponse> playerInfoResponses = playerService.getAllPlayersInfo();

        PlayerInfoResponse response = playerInfoResponses.get(0);
        assertEquals(player.getId(), response.getId());
        assertEquals(player.getName(), response.getName());
        assertEquals(player.getPlayerType(), response.getPlayerType());
        assertEquals(player.getBattingSkill(), response.getBattingSkill());
        assertEquals(player.getBowlingSkill(), response.getBowlingSkill());

        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateTeamPlayers() {
        Player player = Player.builder()
                .id("1")
                .name("testingPlayer")
                .age(5)
                .playerType(PlayerType.ALLROUNDER)
                .battingSkill(55)
                .bowlingSkill(42)
                .numberOfMatches(1)
                .totalRuns(52)
                .centuries(0)
                .highestScore(52)
                .totalWickets(1)
                .mostWickets(1)
                .build();
        List<Player> players = Arrays.asList(player);
        Player mockPlayer = Player.builder()
                .id("1")
                .name("testingPlayer")
                .age(5)
                .playerType(PlayerType.ALLROUNDER)
                .battingSkill(55)
                .bowlingSkill(42)
                .numberOfMatches(5)
                .totalRuns(148)
                .centuries(1)
                .highestScore(102)
                .totalWickets(2)
                .mostWickets(2)
                .build();

        Team mockTeam = Team.builder().teamName("testTeam").players(players).build();
        when(playerRepository.findById("1")).thenReturn(Optional.of(mockPlayer));
        doNothing().when(playerRepository).deleteById("1");
        when(playerRepository.save(any(Player.class))).thenReturn(mockPlayer);
        playerService.updateTeamPlayers(mockTeam);
        //Since it's a void method we can just check number of times a method is called.
        verify(playerRepository, times(1)).findById("1");
        verify(playerRepository, times(1)).deleteById("1");
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    public void testUpdateTeamPlayers_PlayerNotFoundInDb() {
        Player player = Player.builder()
                .id("1")
                .name("testingPlayer")
                .playerType(PlayerType.ALLROUNDER)
                .battingSkill(55)
                .bowlingSkill(42)
                .numberOfMatches(1)
                .totalRuns(52)
                .centuries(0)
                .highestScore(52)
                .totalWickets(1)
                .mostWickets(1)
                .build();
        List<Player> players = Arrays.asList(player);

        Team mockTeam = Team.builder().teamName("testTeam").players(players).build();
        when(playerRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(InvalidUserInputException.class, () -> playerService.updateTeamPlayers(mockTeam));
        verify(playerRepository, times(1)).findById("1");
    }
}
