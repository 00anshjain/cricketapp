package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.service.impl.MatchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchServiceImpl matchServiceImpl;

//    @PostMapping("")
//    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest matchRequest) {
//        return new ResponseEntity<Match>(matchService.createMatch(matchRequest), HttpStatus.OK);
//    }
}
