package pl.envelo.meetek.integration.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.comment.EventCommentRepo;
import pl.envelo.meetek.domain.comment.EventCommentService;
import pl.envelo.meetek.domain.comment.model.Comment;
import pl.envelo.meetek.domain.comment.model.EventComment;
import pl.envelo.meetek.domain.comment.model.EventCommentCreateDto;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.domain.event.SingleEventRepo;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class EventCommentServiceIntegrationTest {

    @Autowired
    EventCommentService commentService;

    @Autowired
    EventCommentRepo commentRepo;

    @Autowired
    StandardUserRepo userRepo;

    @Autowired
    SingleEventRepo eventRepo;


    @Test
    void createEventComment_whenDataValid_integration(){
        // given
        EventCommentCreateDto commentCreateDto= new EventCommentCreateDto("comment", null);
        StandardUser user = userRepo.save(new StandardUser(0L));
        SingleEvent event = eventRepo.save(new SingleEvent(0L,"event"));

        // when
        EventCommentDto commentDto = commentService.createEventComment(user, event, null, commentCreateDto);
        // then
        assertThat(commentDto.getComment()).isEqualTo(commentCreateDto.getComment());
        assertThat(commentDto.getEventId()).isEqualTo(event.getEventId());
        assertThat(commentDto.getCommentOwner().getParticipantId()).isEqualTo(user.getParticipantId());
    }

    @Test
    void createEventComment_whenReplyCommentValid_integration(){
        // given
        EventCommentCreateDto commentCreateDto= new EventCommentCreateDto("comment", null);
        StandardUser user = userRepo.save(new StandardUser(0L));
        SingleEvent event = eventRepo.save(new SingleEvent(0L,"event"));
        Comment replyedComment = commentRepo.save(new EventComment(
                0L,
                LocalDateTime.now(),
                "comment1",
                user,
                event,
                null,
                null));

        // when
        EventCommentDto commentDto = commentService.createEventComment(
                user, event, replyedComment.getCommentId(), commentCreateDto);
        // then
        assertThat(commentDto.getComment()).isEqualTo(commentCreateDto.getComment());
        assertThat(commentDto.getEventId()).isEqualTo(event.getEventId());
        assertThat(commentDto.getCommentOwner().getParticipantId()).isEqualTo(user.getParticipantId());
        assertThat(commentDto.getReplyToComment().getCommentId()).isEqualTo(replyedComment.getCommentId());
    }

    @Test
    void createEventComment_whenReplyCommentIsFromDifferentEvent_integration(){
        // given
        EventCommentCreateDto commentCreateDto= new EventCommentCreateDto("comment", null);
        StandardUser user = userRepo.save(new StandardUser(0L));
        SingleEvent event1 = eventRepo.save(new SingleEvent(0L,"event"));
        SingleEvent event2 = eventRepo.save(new SingleEvent(0L,"event2"));
        Comment replyedComment = commentRepo.save(new EventComment(
                0L,
                LocalDateTime.now(),
                "comment1",
                user,
                event2,
                null,
                null));

        // when
        // then
        assertThrows(ProcessingException.class,() -> commentService.createEventComment(
                user, event1, replyedComment.getCommentId(), commentCreateDto));
    }

    @Test
    void createEventComment_whenReplyCommentNotFound_integration(){
        // given
        EventCommentCreateDto commentCreateDto= new EventCommentCreateDto("comment", null);
        StandardUser user = userRepo.save(new StandardUser(0L));
        SingleEvent event1 = eventRepo.save(new SingleEvent(0L,"event"));

        // when
        // then
        assertThrows(NotFoundException.class,() -> commentService.createEventComment(
                user, event1, 0L, commentCreateDto));
    }

    @Test
    void createEventComment_whenReplyCommentSizeInvalid_integration(){
        // given
        EventCommentCreateDto commentCreateDto= new EventCommentCreateDto("a", null);
        StandardUser user = userRepo.save(new StandardUser(0L));
        SingleEvent event1 = eventRepo.save(new SingleEvent(0L,"event"));

        // when
        // then
        assertThrows(ArgumentNotValidException.class,() -> commentService.createEventComment(
                user, event1, null, commentCreateDto));
    }

    @Test
    void getEventCommentById_whenFound_integration(){
        // given
        EventComment comment = commentRepo.save(new EventComment("aaaaaa", null));
        // when
        EventCommentDto commentDto = commentService.getEventCommentById(comment.getCommentId());
        // then
        assertThat(commentDto.getCommentId()).isEqualTo(comment.getCommentId());
    }

    @Test
    void getEventCommentById_whenNotFound_integration(){
        // given

        // when
        // then
        assertThrows(NotFoundException.class,() -> commentService.getEventCommentById(
                1L));
    }

}
