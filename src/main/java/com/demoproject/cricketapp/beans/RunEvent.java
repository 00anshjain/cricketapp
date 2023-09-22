package com.demoproject.cricketapp.beans;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RunEvent extends BallEvent {
    static final List<String> run = Arrays.asList("0", "1", "2", "3", "4", "5", "6");
    static final List<Double> freq = Arrays.asList(0.25, 0.25, 0.2, 0.05, 0.15, 0.0, 0.1);
    static final List<Double> prefix = Arrays.asList(0.25, 0.5, 0.7, 0.75, 0.90, 0.90, 1.0);

    private int findCielInPrefix(double val)
    {
        int high = prefix.size()-1;
        int low = 0, index = -1;
        while(low <= high)
        {
            int mid = low + (high - low)/2;
            if(prefix.get(mid) >= val){
                index = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return index;
    }
    public String getBallResult() {
        double random = Math.random();
        return run.get(findCielInPrefix(random));
//        return "1";
    }

}
