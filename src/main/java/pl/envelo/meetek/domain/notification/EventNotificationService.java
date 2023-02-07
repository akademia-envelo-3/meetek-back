package pl.envelo.meetek.domain.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.notification.model.EventNotification;
import pl.envelo.meetek.domain.notification.repo.EventNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EventNotificationService {

    private final EventNotificationRepo eventNotificationRepo;

    @Transactional
    public void createEventNotification(EventNotification eventNotification) {
        eventNotificationRepo.save(eventNotification);
    }

    @Transactional(readOnly = true)
    public Optional<EventNotification> getEventNotificationById(long eventNotificationId) {
        return eventNotificationRepo.findById(eventNotificationId);
    }

}
