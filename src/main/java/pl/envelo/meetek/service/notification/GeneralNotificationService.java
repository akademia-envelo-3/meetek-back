package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.notification.GeneralNotification;
import pl.envelo.meetek.repository.notification.GeneralNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GeneralNotificationService {

    private final GeneralNotificationRepo generalNotificationRepo;

    public GeneralNotification createNotification(GeneralNotification generalNotification) {
        return generalNotificationRepo.save(generalNotification);
    }

    public Optional<GeneralNotification> getById(long notificationId) {
        return generalNotificationRepo.findById(notificationId);
    }

}
