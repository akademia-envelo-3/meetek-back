package pl.envelo.meetek.domain.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.notification.model.GroupNotification;
import pl.envelo.meetek.domain.notification.repo.GroupNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupNotificationService {

    private final GroupNotificationRepo groupNotificationRepo;

    @Transactional
    public GroupNotification createNotification(GroupNotification groupNotification) {
        return groupNotificationRepo.save(groupNotification);
    }

    @Transactional(readOnly = true)
    public Optional<GroupNotification> getById(long notificationId) {
        return groupNotificationRepo.findById(notificationId);
    }

}
