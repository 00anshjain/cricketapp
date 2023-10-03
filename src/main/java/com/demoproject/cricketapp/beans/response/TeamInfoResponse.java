package com.demoproject.cricketapp.beans.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamInfoResponse {
    private String id;
    private String teamName;
    private String captainId;

}
