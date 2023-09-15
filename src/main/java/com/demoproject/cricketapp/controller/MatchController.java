package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

//    @PostMapping("")
//    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest matchRequest) {
//        return new ResponseEntity<Match>(matchService.createMatch(matchRequest), HttpStatus.OK);
//    }
}
