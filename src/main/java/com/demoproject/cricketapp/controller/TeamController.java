package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
import com.demoproject.cricketapp.service.PlayerTeamService;
import com.demoproject.cricketapp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final PlayerTeamService playerTeamService;
    @PostMapping("")
    public ResponseEntity<Team> addTeam(@RequestBody TeamRequest teamRequest) {
        return new ResponseEntity<Team>(teamService.addTeam(teamRequest), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamInfoResponse>> getAllTeamsInfo() {
        return new ResponseEntity<List<TeamInfoResponse>>(teamService.getAllTeamsInfo(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return new ResponseEntity<Team>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping("{teamId}/player/{playerId}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable String teamId, @PathVariable String playerId) {
        return new ResponseEntity<Team>(playerTeamService.addPlayerToTeam(teamId, playerId), HttpStatus.OK);
    }

    @DeleteMapping("{teamId}/player/{playerId}")
    public ResponseEntity<Team> dropPlayerFromTeam(@PathVariable String teamId, @PathVariable String playerId) {
        return new ResponseEntity<Team>(playerTeamService.dropPlayerFromTeam(teamId, playerId), HttpStatus.OK);
    }

}
