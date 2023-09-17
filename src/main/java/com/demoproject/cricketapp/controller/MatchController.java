package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.beans.response.MatchInfoResponse;
import com.demoproject.cricketapp.facade.MatchFacade;
import com.demoproject.cricketapp.service.MatchService;
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
    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest matchRequest) {
        return new ResponseEntity<Match>(matchFacade.playMatch(matchRequest), HttpStatus.OK);
    }
}
