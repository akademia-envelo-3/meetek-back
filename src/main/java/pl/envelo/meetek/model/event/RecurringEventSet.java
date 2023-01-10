package pl.envelo.meetek.model.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecurringEventSet {

    private Long eventSetId;
    private Set<Event> events;
    private int eventFrequency;
    private int recursiveCount;

}
