package pl.envelo.meetek.domain.event.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recurring_events")
public class RecurringEventSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventSetId;

    @OneToMany
    @JoinTable(name = "recurring_events_x_events",
            joinColumns = @JoinColumn(name = "event_set_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<SingleEvent> events;
    @NotNull(message = "Field must not be null")
    @Min(value = 1, message = "The value must be at least {value}")
    @Max(value = 14, message = "The max value is {value}")
    private Integer eventFrequency;
    @NotNull(message = "Field must not be null")
    @Min(value = 1, message = "The value must be at least {value}")
    @Max(value = 5, message = "The max value is {value}")
    private Integer recursiveCount;

    @Override
    public String toString() {
        return "RecurringEventSet{" +
                "eventSetId=" + eventSetId +
                ", events=" + events +
                ", eventFrequency=" + eventFrequency +
                ", recursiveCount=" + recursiveCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecurringEventSet that = (RecurringEventSet) o;
        return eventFrequency == that.eventFrequency && recursiveCount == that.recursiveCount && Objects.equals(eventSetId, that.eventSetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventSetId, eventFrequency, recursiveCount);
    }
}
