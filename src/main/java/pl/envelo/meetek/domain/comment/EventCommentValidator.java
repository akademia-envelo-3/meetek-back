package pl.envelo.meetek.domain.comment;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class EventCommentValidator extends ValidatorService<EventComment> {

    private final EventCommentRepo eventCommentRepo;

    public EventCommentValidator(Validator validator, EventCommentRepo eventCommentRepo) {
        super(validator);
        this.eventCommentRepo = eventCommentRepo;
    }

    @Override
    public EventComment validateExists(long id) {
        Optional<EventComment> eventComment = eventCommentRepo.findById(id);
        if (eventComment.isEmpty()) {
            throw new NotFoundException("Event comment with id " + id + " not found");
        }
        return eventComment.get();
    }

    public EventComment validateCommentedComment(Long commentedCommentId, SingleEvent singleEvent) {
        EventComment eventComment = null;
        if (commentedCommentId != null) {
            Optional<EventComment> commentedComment = eventCommentRepo.findById(commentedCommentId);
            if (commentedComment.isEmpty()) {
                throw new NotFoundException("Comment being replied to with id " + commentedCommentId + " not found");
            }
            eventComment = commentedComment.get();
            if (!eventComment.getEvent().getEventId().equals(singleEvent.getEventId())) {
                throw new ProcessingException("Comment cannot be a reply to a comment of another event");
            }
        }
        return eventComment;
    }

}
