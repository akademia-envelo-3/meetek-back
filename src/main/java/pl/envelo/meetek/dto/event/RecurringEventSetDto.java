package pl.envelo.meetek.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecurringEventSetDto {

    private long eventSetId;
    private Set<SingleEventShortDto> events;
    private int eventFrequency;
    private int recursiveCount;

}
