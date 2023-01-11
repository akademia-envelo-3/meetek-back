package pl.envelo.meetek.model.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class EventNotification extends Notification {

    @ManyToOne
    private Event event;

}
