package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;
    @ManyToMany
    private Set<Hashtag> hashtags;
    @ManyToOne
    private AppUser owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", hashtags=" + hashtags +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", dateTimeFrom=" + dateTimeFrom +
                ", dateTimeTo=" + dateTimeTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId) && Objects.equals(hashtags, event.hashtags) && Objects.equals(owner, event.owner) && Objects.equals(name, event.name) && Objects.equals(link, event.link) && Objects.equals(description, event.description) && Objects.equals(dateTimeFrom, event.dateTimeFrom) && Objects.equals(dateTimeTo, event.dateTimeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, hashtags, owner, name, link, description, dateTimeFrom, dateTimeTo);
    }
}
