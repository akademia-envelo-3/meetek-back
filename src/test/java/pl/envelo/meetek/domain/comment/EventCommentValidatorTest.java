package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventCommentValidatorTest {
    @Mock
    private EventCommentRepo eventCommentRepo;

    @InjectMocks
    private EventCommentValidator eventCommentValidator;

    //Exists
    @Test
    void testValidate_WhenEventCommentExists() {
        EventComment eventComment = new EventComment();
        eventComment.setCommentId(1L);
        when(eventCommentRepo.findById(1L)).thenReturn(Optional.of(eventComment));
        EventCommentValidator validator = new EventCommentValidator(null, eventCommentRepo);

        EventComment validatedEventComment = validator.validateExists(1L);

        assertEquals(eventComment, validatedEventComment);
    }

    //Doesn't Exist
    @Test
    void testThrowNotFoundException_WhenEventCommentDoesNotExists() {
        when(eventCommentRepo.findById(1L)).thenReturn(Optional.empty());
        EventCommentValidator validator = new EventCommentValidator(null, eventCommentRepo);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Event comment with id 1 not found", exception.getMessage());
    }

    @Test
    public void testValidateCommentedComment_WithExistingComment_ReturnsEventComment() {
        Long commentedCommentId = 1L;
        SingleEvent singleEvent = new SingleEvent();
        singleEvent.setEventId(1L);
        EventComment commentedComment = new EventComment();
        commentedComment.setEvent(singleEvent);

        when(eventCommentRepo.findById(commentedCommentId)).thenReturn(Optional.of(commentedComment));

        EventComment result = eventCommentValidator.validateCommentedComment(commentedCommentId, singleEvent);

        assertEquals(commentedComment, result);
        verify(eventCommentRepo, times(1)).findById(commentedCommentId);
    }

    @Test
    public void testValidateCommentedComment_WithNonExistingComment_ThrowsNotFoundException() {
        Long commentedCommentId = 1L;
        SingleEvent singleEvent = new SingleEvent();
        when(eventCommentRepo.findById(commentedCommentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> eventCommentValidator.validateCommentedComment(commentedCommentId, singleEvent));
        verify(eventCommentRepo, times(1)).findById(commentedCommentId);
    }

    @Test
    public void testValidateCommentedComment_WithCommentOfDifferentEvent_ThrowsProcessingException() {
        Long commentedCommentId = 1L;
        SingleEvent singleEvent = new SingleEvent();
        singleEvent.setEventId(1L);
        EventComment commentedComment = new EventComment();
        SingleEvent differentEvent = new SingleEvent();
        differentEvent.setEventId(2L);
        commentedComment.setEvent(differentEvent);
        when(eventCommentRepo.findById(commentedCommentId)).thenReturn(Optional.of(commentedComment));

        assertThrows(ProcessingException.class, () -> eventCommentValidator.validateCommentedComment(commentedCommentId, singleEvent));
        verify(eventCommentRepo, times(1)).findById(commentedCommentId);
    }
}
