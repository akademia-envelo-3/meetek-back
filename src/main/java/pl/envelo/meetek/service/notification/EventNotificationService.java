package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.notification.EventNotificationRepo;

@AllArgsConstructor
@Service
public class EventNotificationService {

    private final EventNotificationRepo eventNotificationRepo;
}
