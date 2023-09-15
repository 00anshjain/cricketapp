package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.facade.CricketServicesFacade;
import com.demoproject.cricketapp.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerController {
    private final CricketServicesFacade cricketServicesFacade;
    @PostMapping("")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerRequest playerRequest) {
        return new ResponseEntity<Player>(cricketServicesFacade.addPlayer(playerRequest), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<PlayerInfoResponse>> getAllPlayersInfo() {
        return new ResponseEntity<List<PlayerInfoResponse>>(cricketServicesFacade.getAllPlayersInfo(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        return new ResponseEntity<Player>(cricketServicesFacade.getPlayerById(id), HttpStatus.OK);
    }

}
