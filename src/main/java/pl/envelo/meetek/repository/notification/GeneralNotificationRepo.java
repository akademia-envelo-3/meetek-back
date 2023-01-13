package pl.envelo.meetek.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.GeneralNotification;

@Repository
public interface GeneralNotificationRepo extends JpaRepository<GeneralNotification, Long> {
}
