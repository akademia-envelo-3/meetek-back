package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.repository.comment.RequestCommentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RequestCommentService {

    private final RequestCommentRepo requestCommentRepo;

    public RequestComment createRequestComment(RequestComment requestComment) {
        return requestCommentRepo.save(requestComment);
    }

    public Optional<RequestComment> getRequestCommentById(long requestCommentId) {
        return requestCommentRepo.findById(requestCommentId);
    }
}
