package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.facade.MatchFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchFacade matchFacade;

    @GetMapping("")
    public ResponseEntity<List<MatchInfoResponse>> getAllMatches() {
        return new ResponseEntity<List<MatchInfoResponse>>(matchFacade.getAllMatches(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Match> createAndPlayMatch(@RequestBody MatchRequest matchRequest) {
        return new ResponseEntity<Match>(matchFacade.createAndPlayMatch(matchRequest), HttpStatus.OK);
    }
    @GetMapping("{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable String matchId) {
        return new ResponseEntity<Match>(matchFacade.getMatchById(matchId), HttpStatus.OK);
    }

    @GetMapping("{matchId}/scoreboard")
    public ResponseEntity<Scoreboard> getMatchScoreboard(@PathVariable String matchId) {
        return new ResponseEntity<Scoreboard>(matchFacade.getMatchScoreboard(matchId), HttpStatus.OK);
    }


    @GetMapping("{matchId}/history")
    public ResponseEntity<List<BallEvent>> getAllBallEventsInMatch(@PathVariable String matchId) {
        return new ResponseEntity<List<BallEvent>>(matchFacade.getAllBallEventsInMatch(matchId), HttpStatus.OK);
    }
}
