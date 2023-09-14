package com.demoproject.cricketapp.controller;

import com.demoproject.cricketapp.beans.Player;
import com.demoproject.cricketapp.beans.request.PlayerRequest;
import com.demoproject.cricketapp.beans.response.PlayerInfoResponse;
import com.demoproject.cricketapp.service.MatchService;
import com.demoproject.cricketapp.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

}
