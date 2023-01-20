package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.event.Event;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.repository.comment.EventCommentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EventCommentService {

    private final EventCommentRepo eventCommentRepo;


    public Optional<EventComment> getEventCommentById(long commentId) {

        return eventCommentRepo.findById(commentId);
    }

    public EventComment saveNewEventComment(EventComment eventComment) {
        return eventCommentRepo.save(eventComment);
    }
    public void deleteById(long commentId) {
        eventCommentRepo.deleteById(commentId);
    }
}
