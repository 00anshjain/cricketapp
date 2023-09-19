package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.BallEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BallEventService {
    void save(BallEvent ballEvent);
    List<BallEvent> getAllBallEventInMatch(String matchId);
}
