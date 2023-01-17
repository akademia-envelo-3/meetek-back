package pl.envelo.meetek.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.NotificationCategory;

@Repository
public interface NotificationCategoryRepo extends JpaRepository<NotificationCategory, Long> {
}
