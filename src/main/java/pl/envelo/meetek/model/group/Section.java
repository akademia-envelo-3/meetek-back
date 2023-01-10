package pl.envelo.meetek.model.group;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Section extends Group {

    @OneToMany
    private Set<AppUser> joinedUsers;
    @OneToMany
    private Set<Event> events;
    @OneToMany
    private Set<RecurringEventSet> recurringEvents;
    @ManyToOne
    private AppUser sectionOwner;

    public Section(Long groupId, String name, String description, boolean isActive, Set<AppUser> joinedUsers, Set<Event> events, Set<RecurringEventSet> recurringEvents, AppUser sectionOwner) {
        super(groupId, name, description, isActive);
        this.joinedUsers = joinedUsers;
        this.events = events;
        this.recurringEvents = recurringEvents;
        this.sectionOwner = sectionOwner;
    }
}
