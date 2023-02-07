package pl.envelo.meetek.domain.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.event.model.Event;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications_event")
public class EventNotification extends Notification {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Override
    public String toString() {
        return super.toString() + " EventNotification{" +
                "event=" + event +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventNotification that = (EventNotification) o;
        return Objects.equals(event, that.event) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
