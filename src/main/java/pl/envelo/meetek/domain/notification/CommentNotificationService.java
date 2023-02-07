package pl.envelo.meetek.domain.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.notification.model.CommentNotification;
import pl.envelo.meetek.domain.notification.repo.CommentNotificationRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentNotificationService {

    private final CommentNotificationRepo commentNotificationRepo;

    @Transactional(readOnly = true)
    public Optional<CommentNotification> getCommentNotificationById(long commentNotificationId) {
        return commentNotificationRepo.findById(commentNotificationId);
    }

    @Transactional
    public void createCommentNotification(CommentNotification commentNotification) {
        commentNotificationRepo.save(commentNotification);
    }
}
