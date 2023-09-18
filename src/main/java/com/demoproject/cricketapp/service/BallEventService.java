package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.BallEvent;
import org.springframework.stereotype.Service;

@Service
public interface BallEventService {
    void save(BallEvent ballEvent);
}
