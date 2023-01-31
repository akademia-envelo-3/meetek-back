package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.repository.comment.RequestCommentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RequestCommentService {

    private final RequestCommentRepo requestCommentRepo;

    @Transactional
    public RequestComment createRequestComment(RequestComment requestComment) {
        return requestCommentRepo.save(requestComment);
    }
    @Transactional(readOnly = true)
    public Optional<RequestComment> getRequestCommentById(long requestCommentId) {
        return requestCommentRepo.findById(requestCommentId);
    }

}
