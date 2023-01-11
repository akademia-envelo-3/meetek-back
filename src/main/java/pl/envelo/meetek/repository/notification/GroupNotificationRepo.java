package pl.envelo.meetek.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.notification.GroupNotification;

@Repository
public interface GroupNotificationRepo extends CrudRepository<GroupNotification, Long> {
}
