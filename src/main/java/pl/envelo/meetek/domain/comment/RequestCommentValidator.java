package pl.envelo.meetek.domain.comment;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class RequestCommentValidator extends ValidatorService<RequestComment> {

    private final RequestCommentRepo requestCommentRepo;

    public RequestCommentValidator(Validator validator, RequestCommentRepo requestCommentRepo) {
        super(validator);
        this.requestCommentRepo = requestCommentRepo;
    }

    @Override
    public RequestComment validateExists(long id) {
        Optional<RequestComment> categoryRequest = requestCommentRepo.findById(id);
        if (categoryRequest.isEmpty()) {
            throw new NotFoundException("Request comment with id " + id + " not found");
        }
        return categoryRequest.get();
    }

}
