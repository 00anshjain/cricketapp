package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.repository.BallEventRepository;
import com.demoproject.cricketapp.service.BallEventService;
import com.demoproject.cricketapp.utils.MatchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BallEventServiceImpl implements BallEventService {

    private final BallEventRepository ballEventRepository;
    public void save(BallEvent ballEvent)
    {
        ballEventRepository.save(ballEvent);
    }
    public List<BallEvent> getAllBallEventInMatch(String matchId) {
        List<BallEvent> ballEvents = ballEventRepository.findAllByMatchId(matchId);
        Collections.sort(ballEvents, MatchUtils.comp);
        return ballEvents;
    }
}
