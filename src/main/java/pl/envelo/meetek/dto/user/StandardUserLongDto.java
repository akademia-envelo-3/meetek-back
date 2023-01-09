package pl.envelo.meetek.dto.user;

import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.dto.group.SectionShortDto;
import pl.envelo.meetek.dto.NotificationDto;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.user.Role;

import java.util.Map;
import java.util.Set;

public class StandardUserLongDto {

    private long participantId;
    private String firstname;
    private String lastname;
    private String mail;
    private Role role;
    private Set<SingleEventShortDto> ownedEvents;
    private Map<SingleEventShortDto, EventResponse> eventsWithResponse;
    private Set<SectionShortDto> ownedGroups;
    private Set<SectionShortDto> joinedGroups;
    private Set<NotificationDto> notifications;

}
