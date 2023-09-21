package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.BallEvent;

import java.util.List;


public interface BallEventService {
    void save(BallEvent ballEvent);
    List<BallEvent> getAllBallEventInMatch(String matchId);
}
