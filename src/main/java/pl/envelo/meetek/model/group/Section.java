package pl.envelo.meetek.model.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Section extends Group {

    private Set<AppUser> joinedUsers;
    private Set<Event> events;
    private Set<RecurringEventSet> recurringEvents;
    private AppUser sectionOwner;


}
