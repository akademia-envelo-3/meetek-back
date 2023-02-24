package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.exceptions.NotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventCommentValidatorTest {
    @Mock
    private EventCommentRepo eventCommentRepo;

    @InjectMocks
    private EventCommentValidator eventCommentValidator;

    //Exists
    @Test
    void testValidateIfEventCommentExists() {
        EventComment eventComment = new EventComment();
        eventComment.setCommentId(1L);
        when(eventCommentRepo.findById(1L)).thenReturn(Optional.of(eventComment));
        EventCommentValidator validator = new EventCommentValidator(null, eventCommentRepo);

        EventComment validatedEventComment = validator.validateExists(1L);

        assertEquals(eventComment, validatedEventComment);
    }

    //Doesn't Exist
    @Test
    void testThrowNotFoundException_EventCommentDoesNotExists() {
        when(eventCommentRepo.findById(1L)).thenReturn(Optional.empty());
        EventCommentValidator validator = new EventCommentValidator(null, eventCommentRepo);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Comment being replied to with id 1 not found", exception.getMessage());
    }
}
