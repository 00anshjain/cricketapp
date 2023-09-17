package com.demoproject.cricketapp.utils;

import com.demoproject.cricketapp.beans.Match;
import com.demoproject.cricketapp.beans.Scoreboard;
import com.demoproject.cricketapp.beans.Team;
import com.demoproject.cricketapp.beans.Toss;
import com.demoproject.cricketapp.beans.request.MatchRequest;
import com.demoproject.cricketapp.commons.enums.TossChoice;
import com.demoproject.cricketapp.exception.custom.InvalidMatchRequestException;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;
import java.util.Random;


import java.util.UUID;


@UtilityClass
public class MatchUtils {
    public static void validateTeamForMatch(Team team) {
        if(team.getPlayers().size() != 11) {
            throw new InvalidMatchRequestException("Team doesn't satisfy player count to play match.");
        }
        if(team.getCaptainId() == null || team.getCaptainId().isEmpty()) {
            throw new InvalidMatchRequestException("Team should have a captain assigned to play match.");
        }
    }

    public static Toss haveToss(Team team1, Team team2) {
        Random random = new Random();
        Toss toss = new Toss();

        toss.setTossWonByTeamId(random.nextInt(2) == 0 ? team1.getId() : team2.getId());
        toss.setTossChoice(random.nextInt(2) == 0 ? TossChoice.BAT : TossChoice.BOWL);

        return toss;
    }

}
