package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    @PostMapping("")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerRequest playerRequest) {
        return new ResponseEntity<Player>(playerService.addPlayer(playerRequest), HttpStatus.OK);
    }
}
