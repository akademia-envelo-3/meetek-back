package pl.envelo.meetek.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventNotification extends Notification {

    private Event event;

}
