package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.comment.model.RequestCommentDto;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestCommentServiceTest {

    @Mock
    private RequestCommentRepo requestCommentRepo;

    @Mock
    private RequestCommentValidator requestCommentValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private RequestCommentService requestCommentService;

    //Tests to get Request Comment by id: Success and Failure
    @Test
    void testGetRequestCommentById_ResultSuccess() {
        long requestCommentId = 1L;
        RequestComment requestComment = new RequestComment();
        RequestCommentDto expected = new RequestCommentDto();

        when(requestCommentValidator.validateExists(requestCommentId)).thenReturn(requestComment);
        when(mapperService.mapToRequestCommentDto(requestComment)).thenReturn(expected);

        assertNotNull(requestComment);
        RequestCommentDto result = requestCommentService.getRequestCommentById(requestCommentId);

        assertEquals(expected, result);
        verify(requestCommentValidator).validateExists(requestCommentId);
        verify(mapperService).mapToRequestCommentDto(requestComment);
    }

    @Test
    void testGetRequestCommentById_ResultNotFoundException() {
        long requestCommentId = 1L;
        String message = "RequestComment not found";

        when(requestCommentValidator.validateExists(requestCommentId)).thenThrow(new NotFoundException(message));

        assertThrows(NotFoundException.class, () -> requestCommentService.getRequestCommentById(requestCommentId));

        verify(requestCommentValidator).validateExists(requestCommentId);
        verify(mapperService, never()).mapToRequestCommentDto(any());
    }
}

