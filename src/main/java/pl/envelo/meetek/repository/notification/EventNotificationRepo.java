package pl.envelo.meetek.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.comment.EventComment;

@Repository
public interface EventNotificationRepo extends JpaRepository<EventComment, Long> {
}
