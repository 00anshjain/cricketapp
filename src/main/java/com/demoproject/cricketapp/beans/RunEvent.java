package com.demoproject.cricketapp.beans;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RunEvent extends BallEvent {
    public String getBallResult() {
        return "1";
    }

}
