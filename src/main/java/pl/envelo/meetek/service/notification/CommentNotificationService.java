package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.notification.CommentNotificationRepo;

@AllArgsConstructor
@Service
public class CommentNotificationService {

    private final CommentNotificationRepo commentNotificationRepo;
}
