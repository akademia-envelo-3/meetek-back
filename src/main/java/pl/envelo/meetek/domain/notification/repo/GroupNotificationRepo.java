package pl.envelo.meetek.domain.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.notification.model.GroupNotification;

@Repository
public interface GroupNotificationRepo extends JpaRepository<GroupNotification, Long> {
}
