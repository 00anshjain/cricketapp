package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.TossChoice;
import lombok.Data;

@Data
public class Toss {
    private String tossWonByTeamId;
    private TossChoice tossChoice;
}
