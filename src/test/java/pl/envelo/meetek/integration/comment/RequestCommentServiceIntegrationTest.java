package pl.envelo.meetek.integration.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.comment.RequestCommentRepo;
import pl.envelo.meetek.domain.comment.RequestCommentService;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.comment.model.RequestCommentCreateDto;
import pl.envelo.meetek.domain.comment.model.RequestCommentDto;
import pl.envelo.meetek.domain.event.SingleEventRepo;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.AdminRepo;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class RequestCommentServiceIntegrationTest {

    @Autowired
    RequestCommentRepo commentRepo;

    @Autowired
    RequestCommentService commentService;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    SingleEventRepo eventRepo;

    @Test
    void createRequestComment_whenDataValid_integration(){
        // given
        Admin admin = adminRepo.save(new Admin(0L));
        String commentText = "aaaaaa";
        // when
        RequestComment comment = commentService.createRequestComment(admin, commentText);
        // then
        assertThat(comment.getComment()).isEqualTo(commentText);
    }

    @Test
    void createRequestComment_whenDataInvalid_integration(){
        // given
        Admin admin = adminRepo.save(new Admin(0L));
        String commentText = "a";
        // when
        // then
        assertThrows(ArgumentNotValidException.class,() -> commentService.createRequestComment(admin, commentText));
    }

    @Test
    void getRequestCommentById_whenFound_integration(){
        // given
        Admin admin = adminRepo.save(new Admin(0L));
        String commentText = "aaaaaa";
        RequestComment comment = commentService.createRequestComment(admin, commentText);
        // when
        RequestCommentDto commentDto = commentService.getRequestCommentById(comment.getCommentId());
        // then
        assertThat(commentDto.getComment()).isEqualTo(comment.getComment());
    }

    @Test
    void getRequestCommentById_whenNotFound_integration(){
        // given
        // when
        // then
        assertThrows(NotFoundException.class,() -> commentService.getRequestCommentById(1L));
    }

}
