package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.comment.RequestCommentRepo;

@AllArgsConstructor
@Service
public class RequestCommentService {

    private final RequestCommentRepo requestCommentRepo;
}
