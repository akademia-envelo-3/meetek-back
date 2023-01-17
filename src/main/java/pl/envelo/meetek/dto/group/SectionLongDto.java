package pl.envelo.meetek.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.event.RecurringEventSetDto;
import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionLongDto {

    private long groupId;
    private String name;
    private String description;
    private boolean isActive;
    private Set<StandardUserShortDto> joinedUsers;
    private Set<SingleEventShortDto> events;
    private Set<RecurringEventSetDto> recurringEvents;
    private StandardUserShortDto sectionOwner;

}
