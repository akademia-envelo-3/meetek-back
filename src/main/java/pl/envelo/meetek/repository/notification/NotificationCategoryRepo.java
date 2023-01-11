package pl.envelo.meetek.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.NotificationCategory;

@Repository
public interface NotificationCategoryRepo extends CrudRepository<NotificationCategory, Long> {
}
