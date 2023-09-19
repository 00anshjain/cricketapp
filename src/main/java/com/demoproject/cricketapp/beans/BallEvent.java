package com.demoproject.cricketapp.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("ballEvents")
public abstract class BallEvent {
    private String id;
    private String matchID;
    private long ballNumber;
    private Boolean isFirstInnings = true;

    private String battingTeamId;
    private String bowlingTeamId;

    private String batsman1Id; // Use @DBRef to store a reference to the Player document
    private String batsman2Id; // Use @DBRef to store a reference to the Player document

    private String bowlerId; // Use @DBRef to store a reference to the Player document

    private String result;

     public abstract String getBallResult();

    @Override
    public String toString() {
        if(isFirstInnings)
        {
            return "Innings1: Over=" + ballNumber/6 + "." + (ballNumber%6+1) +
                    " id='" + id + '\'' +
                    ", matchID='" + matchID + '\'' +
                    ", isFirstInnings=" + isFirstInnings +
                    ", battingTeamId='" + battingTeamId + '\'' +
                    ", bowlingTeamId='" + bowlingTeamId + '\'' +
                    ", batsman1Id='" + batsman1Id + '\'' +
                    ", batsman2Id='" + batsman2Id + '\'' +
                    ", bowlerId='" + bowlerId + '\'' +
                    ", result='" + result + '\'';
        }
        return "Innings2: Over=" + ballNumber/6 + "." + (ballNumber%6+1) +
                " id='" + id + '\'' +
                ", matchID='" + matchID + '\'' +
                ", isFirstInnings=" + isFirstInnings +
                ", battingTeamId='" + battingTeamId + '\'' +
                ", bowlingTeamId='" + bowlingTeamId + '\'' +
                ", batsman1Id='" + batsman1Id + '\'' +
                ", batsman2Id='" + batsman2Id + '\'' +
                ", bowlerId='" + bowlerId + '\'' +
                ", result='" + result + '\'';
    }
}
