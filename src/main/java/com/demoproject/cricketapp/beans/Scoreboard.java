package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.utils.ScoreboardUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scoreboard implements BallEventObserver{
    private String id;
    private Team team1; // Use @DBRef to store a reference to the Team document
    private Team team2; // Use @DBRef to store a reference to the Team document
    private int team1Score;
    private int team2Score;

    public void update(BallEvent ballEvent) {
        String battingTeamId = ballEvent.getBattingTeamId();
        String bowlingTeamId = ballEvent.getBowlingTeamId();
        String batsmanOnStrikeId = ballEvent.getBatsman1Id();
        String bowlerId = ballEvent.getBowlerId();
        if(Objects.equals(ballEvent.getResult(), "W")) {
            if(Objects.equals(bowlingTeamId, this.getTeam1().getId())) {
                this.setTeam1(ScoreboardUtils.updateBowlerWicket(this.getTeam1(), bowlerId));
            }
            else {
                this.setTeam2(ScoreboardUtils.updateBowlerWicket(this.getTeam2(), bowlerId));
            }
        }
        else {
            if(Objects.equals(battingTeamId, this.getTeam1().getId())) {
                this.setTeam1Score(this.getTeam1Score() + Integer.parseInt(ballEvent.getResult()));
                this.setTeam1(ScoreboardUtils.updateBatsmanRun(this.getTeam1(), batsmanOnStrikeId, ballEvent.getResult()));
            }
            else {
                this.setTeam2Score(this.getTeam2Score() + Integer.parseInt(ballEvent.getResult()));
                this.setTeam2(ScoreboardUtils.updateBatsmanRun(this.getTeam2(), batsmanOnStrikeId, ballEvent.getResult()));
            }
        }
    }
}
