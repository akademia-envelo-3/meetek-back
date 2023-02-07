package pl.envelo.meetek.domain.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.notification.model.CommentNotification;

@Repository
public interface CommentNotificationRepo extends JpaRepository<CommentNotification, Long> {
}
