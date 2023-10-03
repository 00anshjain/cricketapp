package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.facade.PlayerTeamFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {
    private final PlayerTeamFacade playerTeamFacade;

    @PostMapping("")
    public ResponseEntity<Team> addTeam(@RequestBody TeamRequest teamRequest) {
        return new ResponseEntity<Team>(playerTeamFacade.addTeam(teamRequest), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamInfoResponse>> getAllTeamsInfo() {
        return new ResponseEntity<List<TeamInfoResponse>>(playerTeamFacade.getAllTeamsInfo(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return new ResponseEntity<Team>(playerTeamFacade.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping("{teamId}/player/{playerId}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable String teamId, @PathVariable String playerId) {
        return new ResponseEntity<Team>(playerTeamFacade.addPlayerToTeam(teamId, playerId), HttpStatus.OK);
    }

    @DeleteMapping("{teamId}/player/{playerId}")
    public ResponseEntity<Team> dropPlayerFromTeam(@PathVariable String teamId, @PathVariable String playerId) {
        return new ResponseEntity<Team>(playerTeamFacade.dropPlayerFromTeam(teamId, playerId), HttpStatus.OK);
    }

}
