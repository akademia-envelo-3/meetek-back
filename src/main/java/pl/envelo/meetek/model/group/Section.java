package pl.envelo.meetek.model.group;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.user.AppUser;
import pl.envelo.meetek.model.user.StandardUser;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sections")
public class Section extends Group {

    @ManyToMany
    @JoinTable(name = "sections_x_joined_users",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<StandardUser> joinedUsers;

    @OneToMany
    @JoinTable(name = "sections_x_events",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<SingleEvent> events;

    @OneToMany
    @JoinTable(name = "sections_x_recurring_events",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "recurring_event_id"))
    private Set<RecurringEventSet> recurringEvents;

    @Transient
    private int membersCount;

    public Section(Long groupId, String name, String description, boolean isActive, StandardUser sectionOwner, Set<StandardUser> joinedUsers, Set<SingleEvent> events, Set<RecurringEventSet> recurringEvents) {
        super(groupId, name, description, isActive, sectionOwner);
        this.joinedUsers = joinedUsers;
        this.events = events;
        this.recurringEvents = recurringEvents;
    }

    @Override
    public String toString() {
        return super.toString() + " Section{" +
                "joinedUsers=" + joinedUsers +
                ", events=" + events +
                ", recurringEvents=" + recurringEvents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
