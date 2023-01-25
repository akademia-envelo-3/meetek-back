package pl.envelo.meetek.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.group.Group;
import pl.envelo.meetek.model.notification.Notification;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "standard_users")
public class StandardUser extends AppUser {

    @OneToMany
    @JoinTable(name = "users_x_owned_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> ownedEvents;
    @ManyToMany
    @JoinTable(name = "users_x_events_responses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "response_id"))
    @MapKeyJoinColumn(name = "event_id")
    private Map<Event, EventResponse> eventsWithResponse;
    @OneToMany
    private Set<Group> ownedGroups;
    @ManyToMany
    private Set<Group> joinedGroups;
    @OneToMany
    private Set<Notification> notifications;

    public StandardUser(Long participantId, String firstname, String lastname, String mail, String password, Set<Event> ownedEvents, Map<Event, EventResponse> eventsWithResponse, Set<Group> ownedGroups, Set<Group> joinedGroups, Set<Notification> notifications) {
        super(participantId, firstname, lastname, mail, password, Role.ROLE_USER);
        this.ownedEvents = ownedEvents;
        this.eventsWithResponse = eventsWithResponse;
        this.ownedGroups = ownedGroups;
        this.joinedGroups = joinedGroups;
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return super.toString() + " StandardUser{" +
                "ownedEvents=" + ownedEvents +
                ", eventsWithResponse=" + eventsWithResponse +
                ", ownedGroups=" + ownedGroups +
                ", joinedGroups=" + joinedGroups +
                ", notifications=" + notifications +
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
