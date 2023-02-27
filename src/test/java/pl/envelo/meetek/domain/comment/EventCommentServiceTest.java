package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventCommentServiceTest {

    @Mock
    private EventCommentRepo eventCommentRepo;

    @Mock
    private EventCommentValidator eventCommentValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private EventCommentService eventCommentService;

    //Tests to get Event Comment by id: Success and Failure
    @Test
    void testGetEventCommentById_ResultSuccess() {
        long eventCommentId = 1L;
        EventComment eventComment = new EventComment();
        EventCommentDto expected = new EventCommentDto();

        when(eventCommentValidator.validateExists(eventCommentId)).thenReturn(eventComment);
        when(mapperService.mapToEventCommentDto(eventComment)).thenReturn(expected);

        assertNotNull(eventComment);
        EventCommentDto result = eventCommentService.getEventCommentById(eventCommentId);

        assertEquals(expected, result);
        verify(eventCommentValidator).validateExists(eventCommentId);
        verify(mapperService).mapToEventCommentDto(eventComment);
    }

    @Test
    void testGetEventCommentById_ResultNotFoundException() {
        long eventCommentId = 1L;
        String message = "EventComment not found";

        when(eventCommentValidator.validateExists(eventCommentId)).thenThrow(new NotFoundException(message));

        assertThrows(NotFoundException.class, () -> eventCommentService.getEventCommentById(eventCommentId));

        verify(eventCommentValidator).validateExists(eventCommentId);
        verify(mapperService, never()).mapToEventCommentDto(any());
    }

}
