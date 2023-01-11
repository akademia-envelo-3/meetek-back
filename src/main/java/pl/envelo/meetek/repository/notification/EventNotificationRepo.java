package pl.envelo.meetek.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.comment.EventComment;

@Repository
public interface EventNotificationRepo extends CrudRepository<EventComment, Long> {
}
