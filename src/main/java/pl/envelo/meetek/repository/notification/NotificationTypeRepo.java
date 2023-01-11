package pl.envelo.meetek.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.NotificationType;

@Repository
public interface NotificationTypeRepo extends CrudRepository<NotificationType, Long> {
}
