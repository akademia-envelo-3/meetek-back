package pl.envelo.meetek.service.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.notification.CommentNotification;
import pl.envelo.meetek.repository.notification.CommentNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentNotificationService {

    private final CommentNotificationRepo commentNotificationRepo;

    public Optional<CommentNotification> getCommentNotificationById(long commentNotificationId) {
        return commentNotificationRepo.findById(commentNotificationId);
    }

    public void createCommentNotification(CommentNotification commentNotification) {
        commentNotificationRepo.save(commentNotification);
    }
}
