package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.repository.comment.EventCommentRepo;
import pl.envelo.meetek.service.event.SingleEventService;
import pl.envelo.meetek.service.user.StandardUserService;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventCommentService {

    private final EventCommentRepo eventCommentRepo;
    private final StandardUserService standardUserService;

    @Transactional(readOnly = true)
    public Optional<EventComment> getEventCommentById(long commentId) {
        return eventCommentRepo.findById(commentId);
    }

    @Transactional
    public EventComment saveNewEventComment(long userId, Optional<SingleEvent> singleEvent, Long commentId,EventComment eventComment) {

        Optional<StandardUser> standardUserOptional = standardUserService.getStandardUserById(userId);
        if(commentId != null) {
            Optional<EventComment> eventCommentOptional = eventCommentRepo.findById(commentId);
            if (eventCommentOptional.isPresent() && singleEvent.isPresent()) {
                if (eventCommentOptional.get().getEvent().equals(singleEvent.get())) {
                    eventComment.setReplyToComment(eventCommentOptional.get());
                }
            }
        }
        if(singleEvent.isPresent()){
            eventComment.setAddingDateTime(LocalDateTime.now());
            eventComment.setEvent(singleEvent.get());
            standardUserOptional.ifPresent(eventComment::setCommentOwner);
            return eventCommentRepo.save(eventComment);
        }
        return null;
    }

    @Transactional
    public void deleteById(long commentId) {
        eventCommentRepo.deleteById(commentId);
    }

}
