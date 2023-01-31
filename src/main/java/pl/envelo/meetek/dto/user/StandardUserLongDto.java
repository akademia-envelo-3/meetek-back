package pl.envelo.meetek.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.event.EventResponseDto;
import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.dto.group.SectionShortDto;
import pl.envelo.meetek.dto.notification.NotificationDto;
import pl.envelo.meetek.model.user.Role;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardUserLongDto {

    private long participantId;
    private String firstname;
    private String lastname;
    private String mail;
    private Role role;
    private Set<SingleEventShortDto> ownedEvents;
    private Map<SingleEventShortDto, EventResponseDto> eventsWithResponse;
    private Set<SectionShortDto> ownedGroups;
    private Set<SectionShortDto> joinedGroups;
    private Set<NotificationDto> notifications;

}
