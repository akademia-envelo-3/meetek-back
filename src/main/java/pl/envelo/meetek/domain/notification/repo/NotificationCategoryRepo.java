package pl.envelo.meetek.domain.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.notification.model.NotificationCategory;

@Repository
public interface NotificationCategoryRepo extends JpaRepository<NotificationCategory, Long> {
}
