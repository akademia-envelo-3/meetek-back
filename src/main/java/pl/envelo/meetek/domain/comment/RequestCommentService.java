package pl.envelo.meetek.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.comment.model.RequestCommentDto;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RequestCommentService {

    private final RequestCommentRepo requestCommentRepo;
    private final RequestCommentValidator requestCommentValidator;
    private final DtoMapperService mapperService;

    @Transactional
    public RequestComment createRequestComment(Admin admin, String comment) {
        RequestComment requestComment = new RequestComment();
        requestComment.setCommentOwner(admin);
        requestComment.setComment(comment);
        requestComment.setAddingDateTime(LocalDateTime.now());
        requestCommentValidator.validateInput(requestComment);
        return requestCommentRepo.save(requestComment);
    }

    @Transactional(readOnly = true)
    public RequestCommentDto getRequestCommentById(long requestCommentId) {
        RequestComment requestComment = requestCommentValidator.validateExists(requestCommentId);
        return mapperService.mapToRequestCommentDto(requestComment);
    }

}
