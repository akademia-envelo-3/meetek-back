package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.notification.GroupNotificationRepo;

@AllArgsConstructor
@Service
public class GroupNotificationService {

    private final GroupNotificationRepo groupNotificationRepo;
}
