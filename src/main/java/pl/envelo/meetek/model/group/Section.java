package pl.envelo.meetek.model.group;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Section extends Group {

    @ManyToMany
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

    @Override
    public String toString() {
        return super.toString() + " Section{" +
                "joinedUsers=" + joinedUsers +
                ", events=" + events +
                ", recurringEvents=" + recurringEvents +
                ", sectionOwner=" + sectionOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Section section = (Section) o;
        return Objects.equals(joinedUsers, section.joinedUsers) && Objects.equals(events, section.events) && Objects.equals(recurringEvents, section.recurringEvents) && Objects.equals(sectionOwner, section.sectionOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), joinedUsers, events, recurringEvents, sectionOwner);
    }
}
