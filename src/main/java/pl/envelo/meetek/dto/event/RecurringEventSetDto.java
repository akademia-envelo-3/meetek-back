package pl.envelo.meetek.dto.event;

import java.util.Set;

public class RecurringEventSetDto {

    private long eventSetId;
    private Set<SingleEventShortDto> events;
    private int eventFrequency;
    private int recursiveCount;

}
