package pl.envelo.meetek.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.GeneralNotification;

@Repository
public interface GeneralNotificationRepo extends CrudRepository<GeneralNotification, Long> {
}
