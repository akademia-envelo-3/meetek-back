package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.repository.comment.EventCommentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EventCommentService {

    private final EventCommentRepo eventCommentRepo;

    @Transactional(readOnly = true)
    public Optional<EventComment> getEventCommentById(long commentId) {
        return eventCommentRepo.findById(commentId);
    }

    @Transactional
    public EventComment saveNewEventComment(EventComment eventComment) {
        return eventCommentRepo.save(eventComment);
    }

    @Transactional
    public void deleteById(long commentId) {
        eventCommentRepo.deleteById(commentId);
    }

}
