package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.notification.GeneralNotificationRepo;

@AllArgsConstructor
@Service
public class GeneralNotificationService {

    private final GeneralNotificationRepo generalNotificationRepo;
}
