package pl.envelo.meetek.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.domain.comment.model.EventCommentCreateDto;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class EventCommentService {

    private final EventCommentRepo eventCommentRepo;
    private final EventCommentValidator eventCommentValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public EventCommentDto getEventCommentById(long commentId) {
        EventComment eventComment = eventCommentValidator.validateExists(commentId);
        return mapperService.mapToEventCommentDto(eventComment);
    }

    @Transactional
    public EventCommentDto createEventComment(StandardUser standardUser, SingleEvent singleEvent, Long commentedCommentId, EventCommentCreateDto eventCommentDto) {
        EventComment eventComment = mapperService.mapToEventComment(eventCommentDto);
        EventComment commentedComment = eventCommentValidator.validateCommentedComment(commentedCommentId, singleEvent);
        eventComment.setReplyToComment(commentedComment);
        eventComment.setAddingDateTime(LocalDateTime.now());
        eventComment.setEvent(singleEvent);
        eventComment.setCommentOwner(standardUser);
        eventCommentValidator.validateInput(eventComment);
        eventComment = eventCommentRepo.save(eventComment);
        return mapperService.mapToEventCommentDto(eventComment);

    }

    @Transactional
    public void deleteById(long commentId) {
        eventCommentRepo.deleteById(commentId);
    }

}
