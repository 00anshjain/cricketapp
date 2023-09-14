package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.request.TeamRequest;
import com.demoproject.cricketapp.beans.response.TeamInfoResponse;
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
    @PostMapping("")
    public ResponseEntity<Team> addPlayer(@RequestBody TeamRequest teamRequest) {
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
}
