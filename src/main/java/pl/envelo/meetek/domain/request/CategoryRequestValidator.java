package pl.envelo.meetek.domain.request;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class CategoryRequestValidator extends ValidatorService<CategoryRequest> {

    private final CategoryRequestRepo categoryRequestRepo;

    public CategoryRequestValidator(Validator validator, CategoryRequestRepo categoryRequestRepo) {
        super(validator);
        this.categoryRequestRepo = categoryRequestRepo;
    }

    @Override
    public CategoryRequest validateExists(long id) {
        Optional<CategoryRequest> categoryRequest = categoryRequestRepo.findById(id);
        if (categoryRequest.isEmpty()) {
            throw new NotFoundException("Category request with id " + id + " not found");
        }
        return categoryRequest.get();
    }

    public void validateNotProcessed(CategoryRequest categoryRequest) {
        if (categoryRequest.getStatus() != RequestStatus.NOT_PROCESSED) {
            throw new ProcessingException("Category request has already been processed");
        }
    }

    public void validateRequestStatus(String status) {
        Optional<RequestStatus> requestStatus = RequestStatus.findRequestStatus(status.toUpperCase());
        if (requestStatus.isEmpty()) {
            throw new ArgumentNotValidException("Wrong status, values accepted: " + RequestStatus.ACCEPTED + ", " + RequestStatus.REJECTED);
        }
    }

    public void validateRejection(CategoryRequest categoryRequest) {
        if (categoryRequest.getStatus() == RequestStatus.REJECTED) {
            if (categoryRequest.getComment() == null || categoryRequest.getComment().getComment().isBlank()) {
                throw new ArgumentNotValidException("Category request comment cannot be empty");
            }
        }
    }

}
