package pl.envelo.meetek.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecurringEventSetCreateDto {

    private int eventFrequency;
    private int recursiveCount;

}
