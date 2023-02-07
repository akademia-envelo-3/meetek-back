package pl.envelo.meetek.domain.event.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;

    @Size(max = 100, message = "Can't add more than {max} hashtags.")
    @ManyToMany
    @JoinTable(name = "events_x_hashtags",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private Set<Hashtag> hashtags;

    @NotNull(message = "Field must not be null")
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private StandardUser owner;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 20, message = "Field must be between {min} and {max} characters")
    private String name;
    @NotBlank(message = "Field must not be blank")
    @URL(message = "Field must be URL")
    private String link;
    @NotBlank(message = "Field must not be blank")
    @Size(min = 20, max = 2000, message = "Field must be between {min} and {max} characters")
    private String description;

    @NotNull(message = "Field must not be null")
    @Future(message = "Event must start in future")
    private LocalDateTime dateTimeFrom;
    @NotNull(message = "Field must not be null")
    @Future(message = "Event must end in future")
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
        return Objects.equals(eventId, event.eventId) && Objects.equals(owner, event.owner) && Objects.equals(name, event.name) && Objects.equals(link, event.link) && Objects.equals(description, event.description) && Objects.equals(dateTimeFrom, event.dateTimeFrom) && Objects.equals(dateTimeTo, event.dateTimeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, owner, name, link, description, dateTimeFrom, dateTimeTo);
    }
}
