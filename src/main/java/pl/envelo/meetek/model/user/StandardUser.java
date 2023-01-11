package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.group.Group;
import pl.envelo.meetek.model.notification.Notification;


import java.util.Map;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class StandardUser extends AppUser {

    @OneToMany
    private Set<Event> ownedEvents;
    @ManyToMany
    private Map<Event, EventResponse> eventsWithResponse;
    @OneToMany
    private Set<Group> ownedGroups;
    @ManyToMany
    private Set<Group> joinedGroups;
    @OneToMany
    private Set<Notification> notifications;

    public StandardUser(String password, Set<Event> ownedEvents, Map<Event, EventResponse> eventsWithResponse, Set<Group> ownedGroups, Set<Group> joinedGroups, Set<Notification> notifications) {
        super(password, Role.ROLE_USER);
        this.ownedEvents = ownedEvents;
        //this.eventsWithResponse = eventsWithResponse;
        this.ownedGroups = ownedGroups;
        this.joinedGroups = joinedGroups;
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return super.toString() + "StandardUser{" +
                "ownedEvents=" + ownedEvents +
                ", eventsWithResponse=" + eventsWithResponse +
                ", ownedGroups=" + ownedGroups +
                ", joinedGroups=" + joinedGroups +
                ", notifications=" + notifications +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardUser that = (StandardUser) o;
        return Objects.equals(ownedEvents, that.ownedEvents) && Objects.equals(eventsWithResponse, that.eventsWithResponse) && Objects.equals(ownedGroups, that.ownedGroups) && Objects.equals(joinedGroups, that.joinedGroups) && Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownedEvents, eventsWithResponse, ownedGroups, joinedGroups, notifications);
    }
}
