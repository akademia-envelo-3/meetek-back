package pl.envelo.meetek.model.group;

import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;


public class Section extends Group {

    private Set<AppUser> joinedUsers;
    private Set<Event> events;
    private Set<RecurringEventSet> reccuringEvents;
    private AppUser sectionOwner;


}
