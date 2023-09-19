package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.repository.PlayerRepository;
import com.demoproject.cricketapp.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;
    @Test
    void addPlayer_emptyPlayerObject() {
        Player player = new Player();
        InvalidRequestException invalidRequestException = assertThrows(InvalidRequestException.class, () -> playerService.addPlayer(player));
        assertTrue(invalidRequestException.getErrorMessage().contains("required parameters"));
    }

//    @Test
//    void addPlayer_validPlayerObject() {
//        Player player = Player.builder()
//                .id("1")
//                .name("testPlayer")
//                .playerType(PlayerType.BATSMAN)
//                .battingSkill(50)
//                .bowlingSkill(50)
//                .build();
//        when(playerRepository.save(player))
//        playerService.addPlayer(player);
//    }

}
