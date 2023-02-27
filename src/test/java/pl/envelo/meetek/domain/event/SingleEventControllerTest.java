package pl.envelo.meetek.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.envelo.meetek.domain.comment.EventCommentService;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.domain.event.model.SingleEventLongDto;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.user.StandardUserService;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleEventControllerTest {

    @Mock
    private SingleEventService singleEventService;

    @Mock
    private EventCommentService eventCommentService;

    @Mock
    private StandardUserService standardUserService;

    @InjectMocks
    private SingleEventController singleEventController;

    @Test
    public void testGetEvent_ReturnSuccess() {
        long eventId = 1L;
        SingleEventLongDto singleEventLongDto = new SingleEventLongDto();
        singleEventLongDto.setEventId(eventId);

        when(singleEventService.getEventById(eventId)).thenReturn(singleEventLongDto);

        ResponseEntity<SingleEventLongDto> response = singleEventController.getEvent(eventId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(singleEventLongDto, response.getBody());
    }

    @Test
    public void testGetAllPublicFutureNotAcceptedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPublicFutureNotAcceptedEvents(1L, null)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPublicFutureNotAcceptedEvents(1L, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicFutureNotAcceptedEvents_ReturnNoContentStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPublicFutureNotAcceptedEvents(1L, null)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPublicFutureNotAcceptedEvents(1L, null);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicFutureAcceptedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> eventsAccepted = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllFutureAcceptedEvents(1L, null)).thenReturn(eventsAccepted);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllFutureAcceptedEvents(1L, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventsAccepted, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicFutureAcceptedEvents_ReturnNoContentStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> eventsAccepted = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllFutureAcceptedEvents(1L, null)).thenReturn(eventsAccepted);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllFutureAcceptedEvents(1L, null);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(eventsAccepted, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicFutureOwnedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.findAllFutureOwnedByUser(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getFutureOwnedByUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicFutureOwnedEvents_ReturnNoContentStauts() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.findAllFutureOwnedByUser(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getFutureOwnedByUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastNotAcceptedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPublicPastNotAcceptedEvents(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPublicPastNotAcceptedEvents(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastNotAcceptedEvents_ReturnNoContentStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPublicPastNotAcceptedEvents(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPublicPastNotAcceptedEvents(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastAcceptedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPastAcceptedEvents(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPastAcceptedEvents(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastAcceptedEvents_ReturnNoContentStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.getAllPastAcceptedEvents(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getAllPastAcceptedEvents(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastOwnedEvents_ReturnOkStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList(new SingleEventShortDto(), new SingleEventShortDto());

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.findAllPastOwnedByUser(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getPastOwnedByUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublicPastOwnedEvents_ReturnNoContentStatus() {
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        List<SingleEventShortDto> events = Arrays.asList();

        when(standardUserService.getStandardUserById(1L)).thenReturn(user);
        when(singleEventService.findAllPastOwnedByUser(1L)).thenReturn(events);

        ResponseEntity<List<SingleEventShortDto>> responseEntity = singleEventController.getPastOwnedByUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    public void testGetEventComment_ReturnSuccessful() {
        long commentId = 1L;
        EventCommentDto comment = new EventCommentDto();
        comment.setCommentId(commentId);

        when(eventCommentService.getEventCommentById(commentId)).thenReturn(comment);

        ResponseEntity<EventCommentDto> responseEntity = singleEventController.getEventComment(commentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comment, responseEntity.getBody());
    }

}