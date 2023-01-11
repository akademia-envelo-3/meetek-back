package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RecurringEventSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventSetId;
    @OneToMany
    private Set<Event> events;
    private int eventFrequency;
    private int recursiveCount;

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
        return eventFrequency == that.eventFrequency && recursiveCount == that.recursiveCount && Objects.equals(eventSetId, that.eventSetId) && Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventSetId, events, eventFrequency, recursiveCount);
    }
}
