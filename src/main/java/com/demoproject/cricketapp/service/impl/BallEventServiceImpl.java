package com.demoproject.cricketapp.service.impl;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.repository.BallEventRepository;
import com.demoproject.cricketapp.service.BallEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BallEventServiceImpl implements BallEventService {
    private final BallEventRepository ballEventRepository;
    public void save(BallEvent ballEvent)
    {
        ballEventRepository.save(ballEvent);
    }
}
