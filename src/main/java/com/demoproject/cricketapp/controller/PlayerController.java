package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.facade.PlayerTeamFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerTeamFacade playerTeamFacade;
    @PostMapping("")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerRequest playerRequest) {
        return new ResponseEntity<Player>(playerTeamFacade.addPlayer(playerRequest), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<PlayerInfoResponse>> getAllPlayersInfo() {
        return new ResponseEntity<List<PlayerInfoResponse>>(playerTeamFacade.getAllPlayersInfo(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        return new ResponseEntity<Player>(playerTeamFacade.getPlayerById(id), HttpStatus.OK);
    }

}
