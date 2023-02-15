package pl.envelo.meetek.domain.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.notification.model.GeneralNotification;
import pl.envelo.meetek.domain.notification.repo.GeneralNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GeneralNotificationService {

    private final GeneralNotificationRepo generalNotificationRepo;

    @Transactional
    public GeneralNotification createNotification(GeneralNotification generalNotification) {
        return generalNotificationRepo.save(generalNotification);
    }

    @Transactional(readOnly = true)
    public Optional<GeneralNotification> getById(long notificationId) {
        return generalNotificationRepo.findById(notificationId);
    }

}
