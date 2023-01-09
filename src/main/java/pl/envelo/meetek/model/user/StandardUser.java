package pl.envelo.meetek.model.user;

import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.group.Group;
import pl.envelo.meetek.model.notification.Notification;


import java.util.Map;
import java.util.Set;

public class StandardUser extends AppUser {

    private Set<Event> ownedEvents;
    private Map<Event, EventResponse> eventsWithResponse;
    private Set<Group> ownedGroups;
    private Set<Group> joinedGroups;
    private Set<Notification> notifications;

}
