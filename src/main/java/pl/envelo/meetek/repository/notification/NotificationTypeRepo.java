package pl.envelo.meetek.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.NotificationType;

@Repository
public interface NotificationTypeRepo extends JpaRepository<NotificationType, Long> {
}
