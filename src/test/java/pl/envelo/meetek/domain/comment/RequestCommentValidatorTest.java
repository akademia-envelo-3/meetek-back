package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestCommentValidatorTest {
    @Mock
    private RequestCommentRepo requestCommentRepo;

    @InjectMocks
    private RequestCommentValidator requestCommentValidator;

    @Test
    void testValidateIfRequestCommentExists() {
        RequestComment requestComment = new RequestComment();
        requestComment.setCommentId(1L);
        when(requestCommentRepo.findById(1L)).thenReturn(Optional.of(requestComment));
        RequestCommentValidator validator = new RequestCommentValidator(null, requestCommentRepo);

        RequestComment validatedRequestComment = validator.validateExists(1L);

        assertEquals(requestComment, validatedRequestComment);
    }

    //Doesn't Exist
    @Test
    void testThrowNotFoundException_RequestCommentDoesNotExists() {
        when(requestCommentRepo.findById(1L)).thenReturn(Optional.empty());
        RequestCommentValidator validator = new RequestCommentValidator(null, requestCommentRepo);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Request comment with id 1 not found", exception.getMessage());
    }
}

