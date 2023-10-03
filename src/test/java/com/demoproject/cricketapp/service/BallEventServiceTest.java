package com.demoproject.cricketapp.service;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.beans.RunEvent;
import com.demoproject.cricketapp.beans.WicketEvent;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import com.demoproject.cricketapp.repository.BallEventRepository;
import com.demoproject.cricketapp.service.impl.BallEventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BallEventServiceTest {
    @Mock
    BallEventRepository ballEventRepository;

    @InjectMocks
    BallEventServiceImpl ballEventService;

    @Test
    void testSave_InvalidRunEvent() {
        BallEvent mockBallEvent = new RunEvent();
        assertThrows(InvalidRequestException.class, () -> ballEventService.save(mockBallEvent));

        verify(ballEventRepository, never()).save(mockBallEvent);
    }
    @Test
    void testSave_InvalidWicketEvent() {
        BallEvent mockBallEvent = new WicketEvent();
        assertThrows(InvalidRequestException.class, () -> ballEventService.save(mockBallEvent));

        verify(ballEventRepository, never()).save(mockBallEvent);
    }
}
