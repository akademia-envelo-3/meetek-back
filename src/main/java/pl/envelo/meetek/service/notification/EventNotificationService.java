package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.notification.EventNotification;
import pl.envelo.meetek.repository.notification.EventNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EventNotificationService {

    private final EventNotificationRepo eventNotificationRepo;

    public void createEventNotification(EventNotification eventNotification) {
        eventNotificationRepo.save(eventNotification);
    }

    public Optional<EventNotification> getEventNotificationById(long eventNotificationId) {
        return eventNotificationRepo.findById(eventNotificationId);
    }
}
