package pl.envelo.meetek.domain.comment;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.utils.ValidatorService;

@Service
public class RequestCommentValidator extends ValidatorService<RequestComment> {

    private final RequestCommentRepo requestCommentRepo;

    public RequestCommentValidator(Validator validator, RequestCommentRepo requestCommentRepo) {
        super(validator);
        this.requestCommentRepo = requestCommentRepo;
    }

}
