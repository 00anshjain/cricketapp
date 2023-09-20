package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.BallEvent;
import com.demoproject.cricketapp.exception.custom.InvalidRequestException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BallEventUtils {
    public void validateBallEvent(BallEvent ballEvent) {
        if (ballEvent.getId() == null || ballEvent.getId().isEmpty()) {
            throw new InvalidRequestException("BallEvent must have an Id");
        }
        if (ballEvent.getBallNumber() <= 0){
            throw new InvalidRequestException("Ball event cannot have ball number <= 0.");
        }
        if(ballEvent.getBattingTeamId() == null || ballEvent.getBattingTeamId().isEmpty()) {
            throw new InvalidRequestException("Batting Team should be known for Ball Event to happen.");
        }
        if(ballEvent.getBowlingTeamId() == null || ballEvent.getBowlingTeamId().isEmpty()) {
            throw new InvalidRequestException("Bowling Team should be known for Ball Event to happen.");
        }
        if(ballEvent.getBatsman1Id() == null || ballEvent.getBatsman1Id().isEmpty()) {
            throw new InvalidRequestException("Ball event should have batsman on strike.");
        }
        if(ballEvent.getBatsman2Id() == null || ballEvent.getBatsman2Id().isEmpty()) {
            throw new InvalidRequestException("Ball event should have non strike batsman.");
        }
        if(ballEvent.getBowlerId() == null || ballEvent.getBowlerId().isEmpty()) {
            throw new InvalidRequestException("Ball event should have a bowler.");
        }
        if(ballEvent.getResult() == null || ballEvent.getResult().isEmpty()) {
            throw new InvalidRequestException("Ball event should have a result");
        }
    }
}
